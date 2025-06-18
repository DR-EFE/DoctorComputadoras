package com.primer.secomputadora.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.primer.secomputadora.models.DiagnosticEngine
import com.primer.secomputadora.models.QuestionType


data class QuestionnaireScreen(val symptomId: String) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val diagnosticEngine = remember { DiagnosticEngine() }
        val questions = remember { diagnosticEngine.getQuestionsForSymptom(symptomId) }
        var currentQuestionIndex by remember { mutableIntStateOf(0) }
        var answers by remember { mutableStateOf(mutableMapOf<String, String>()) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Preguntas de diagnóstico") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                )
            }
        ) { paddingValues ->
            if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
                val currentQuestion = questions[currentQuestionIndex]

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    // Indicador de progreso
                    LinearProgressIndicator(
                        progress = (currentQuestionIndex + 1).toFloat() / questions.size.toFloat(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    Text(
                        text = "Pregunta ${currentQuestionIndex + 1} de ${questions.size}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = currentQuestion.text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    when (currentQuestion.type) {
                        QuestionType.YES_NO -> {
                            YesNoQuestion(
                                onAnswer = { answer ->
                                    answers[currentQuestion.id] = answer
                                    proceedToNext(
                                        currentQuestionIndex,
                                        questions.size,
                                        onNext = { currentQuestionIndex++ },
                                        onComplete = {
                                            val result = diagnosticEngine.diagnose(symptomId, answers)
                                            navigator.push(ResultScreen(result))
                                        }
                                    )
                                }
                            )
                        }
                        QuestionType.MULTIPLE_CHOICE -> {
                            MultipleChoiceQuestion(
                                options = currentQuestion.options,
                                onAnswer = { answer ->
                                    answers[currentQuestion.id] = answer
                                    proceedToNext(
                                        currentQuestionIndex,
                                        questions.size,
                                        onNext = { currentQuestionIndex++ },
                                        onComplete = {
                                            val result = diagnosticEngine.diagnose(symptomId, answers)
                                            navigator.push(ResultScreen(result))
                                        }
                                    )
                                }
                            )
                        }
                        QuestionType.VISUAL_SELECTION -> {
                            // Implementar selección visual si es necesario
                        }
                    }
                }
            }
        }
    }

    private fun proceedToNext(
        currentIndex: Int,
        totalQuestions: Int,
        onNext: () -> Unit,
        onComplete: () -> Unit
    ) {
        if (currentIndex + 1 < totalQuestions) {
            onNext()
        } else {
            onComplete()
        }
    }
}

@Composable
fun YesNoQuestion(onAnswer: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { onAnswer("Sí") },
            modifier = Modifier.weight(1f).height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Sí", fontSize = 16.sp)
        }

        OutlinedButton(
            onClick = { onAnswer("No") },
            modifier = Modifier.weight(1f).height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("No", fontSize = 16.sp)
        }
    }
}

@Composable
fun MultipleChoiceQuestion(
    options: List<String>,
    onAnswer: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options) { option ->
            OutlinedButton(
                onClick = { onAnswer(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = option,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}
