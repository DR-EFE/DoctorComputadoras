package com.primer.secomputadora.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.OutlinedButtonDefaults
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.primer.secomputadora.models.DiagnosticEngine
import com.primer.secomputadora.models.QuestionType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class QuestionnaireScreen(val symptomId: String) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val diagnosticEngine = remember { DiagnosticEngine() }
        val questions = remember { diagnosticEngine.getQuestionsForSymptom(symptomId) }
        var currentQuestionIndex by remember { mutableIntStateOf(0) }
        var answers by remember { mutableStateOf(mutableMapOf<String, String>()) }
        var isTransitioning by remember { mutableStateOf(false) }

        // Animaciones para el progreso
        val progressAnimation by animateFloatAsState(
            targetValue = (currentQuestionIndex + 1).toFloat() / questions.size.toFloat(),
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            label = "progress"
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Diagnóstico Inteligente",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        ) { paddingValues ->
            if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
                val currentQuestion = questions[currentQuestionIndex]

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.background,
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
                                )
                            )
                        )
                        .padding(20.dp)
                ) {
                    // Header con progreso mejorado
                    AnimatedProgressHeader(
                        currentQuestion = currentQuestionIndex + 1,
                        totalQuestions = questions.size,
                        progress = progressAnimation
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Pregunta con animación
                    AnimatedVisibility(
                        visible = !isTransitioning,
                        enter = slideInVertically(
                            initialOffsetY = { it / 3 },
                            animationSpec = tween(600)
                        ) + fadeIn(animationSpec = tween(600)),
                        exit = slideOutVertically(
                            targetOffsetY = { -it / 3 },
                            animationSpec = tween(400)
                        ) + fadeOut(animationSpec = tween(400))
                    ) {
                        QuestionCard(
                            question = currentQuestion.text,
                            questionNumber = currentQuestionIndex + 1
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Respuestas con animación
                    AnimatedVisibility(
                        visible = !isTransitioning,
                        enter = slideInVertically(
                            initialOffsetY = { it / 2 },
                            animationSpec = tween(700, delayMillis = 200)
                        ) + fadeIn(animationSpec = tween(700, delayMillis = 200)),
                        exit = slideOutVertically(
                            targetOffsetY = { it / 2 },
                            animationSpec = tween(400)
                        ) + fadeOut(animationSpec = tween(400))
                    ) {
                        when (currentQuestion.type) {
                            QuestionType.YES_NO -> {
                                AnimatedYesNoQuestion { answer ->
                                    processAnswer(
                                        answer = answer,
                                        questionId = currentQuestion.id,
                                        answers = answers,
                                        currentIndex = currentQuestionIndex,
                                        totalQuestions = questions.size,
                                        isTransitioning = isTransitioning,
                                        onTransitionStart = { isTransitioning = true },
                                        onTransitionEnd = { isTransitioning = false },
                                        onNext = { currentQuestionIndex++ },
                                        onComplete = {
                                            val result = diagnosticEngine.diagnose(symptomId, answers)
                                            navigator.push(ResultScreen(result))
                                        }
                                    )
                                }
                            }
                            QuestionType.MULTIPLE_CHOICE -> {
                                AnimatedMultipleChoiceQuestion(
                                    options = currentQuestion.options
                                ) { answer ->
                                    processAnswer(
                                        answer = answer,
                                        questionId = currentQuestion.id,
                                        answers = answers,
                                        currentIndex = currentQuestionIndex,
                                        totalQuestions = questions.size,
                                        isTransitioning = isTransitioning,
                                        onTransitionStart = { isTransitioning = true },
                                        onTransitionEnd = { isTransitioning = false },
                                        onNext = { currentQuestionIndex++ },
                                        onComplete = {
                                            val result = diagnosticEngine.diagnose(symptomId, answers)
                                            navigator.push(ResultScreen(result))
                                        }
                                    )
                                }
                            }
                            QuestionType.VISUAL_SELECTION -> {
                                // Implementar selección visual si es necesario
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun processAnswer(
        answer: String,
        questionId: String,
        answers: MutableMap<String, String>,
        currentIndex: Int,
        totalQuestions: Int,
        isTransitioning: Boolean,
        onTransitionStart: () -> Unit,
        onTransitionEnd: () -> Unit,
        onNext: () -> Unit,
        onComplete: () -> Unit
    ) {
        if (!isTransitioning) {
            answers[questionId] = answer
            onTransitionStart()
            delay(500) // Tiempo para que se complete la animación

            if (currentIndex + 1 < totalQuestions) {
                onNext()
            } else {
                onComplete()
            }
            onTransitionEnd()
        }
    }
}

@Composable
fun AnimatedProgressHeader(
    currentQuestion: Int,
    totalQuestions: Int,
    progress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pregunta $currentQuestion",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "$currentQuestion/$totalQuestions",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Barra de progreso personalizada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.tertiary
                                )
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun QuestionCard(
    question: String,
    questionNumber: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(28.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = questionNumber.toString(),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Responde con sinceridad",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = question,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun AnimatedYesNoQuestion(onAnswer: suspend (String) -> Unit) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Botón Sí
        Button(
            onClick = {
                if (selectedOption == null) {
                    selectedOption = "Sí"
                    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                        delay(200)
                        onAnswer("Sí")
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption == "Sí")
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                else
                    MaterialTheme.colorScheme.primary
            ),
            enabled = selectedOption == null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Sí",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Botón No
        OutlinedButton(
            onClick = {
                if (selectedOption == null) {
                    selectedOption = "No"
                    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                        delay(200)
                        onAnswer("No")
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (selectedOption == "No")
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else
                    Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            border = androidx.compose.foundation.BorderStroke(
                2.dp,
                if (selectedOption == "No")
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                else
                    MaterialTheme.colorScheme.primary
            ),
            enabled = selectedOption == null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "No",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun AnimatedMultipleChoiceQuestion(
    options: List<String>,
    onAnswer: suspend (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(options) { index, option ->
            val delay = index * 100

            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = delay,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = delay
                    )
                )
            ) {
                OutlinedButton(
                    onClick = {
                        if (selectedOption == null) {
                            selectedOption = option
                            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                                delay(200)
                                onAnswer(option)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (selectedOption == option)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        else
                            Color.Transparent,
                        contentColor = if (selectedOption == option)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        2.dp,
                        if (selectedOption == option)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline
                    ),
                    enabled = selectedOption == null
                ) {
                    Text(
                        text = option,
                        fontSize = 15.sp,
                        fontWeight = if (selectedOption == option) FontWeight.Medium else FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

// Eliminamos AnimatedAnswerButton ya que no la usamos más