package com.primer.secomputadora.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.primer.secomputadora.models.DiagnosticResult
import com.primer.secomputadora.models.SeverityLevel
import com.primer.secomputadora.models.Solution

data class ResultScreen(val result: DiagnosticResult) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var completedSteps by remember { mutableStateOf(setOf<Int>()) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Diagnóstico") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                // Volver al inicio
                                navigator.popAll()
                                navigator.push(WelcomeScreen())
                            }
                        ) {
                            Icon(Icons.Default.Home, contentDescription = "Inicio")
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Resultado principal
                item {
                    ResultCard(result = result)
                }

                // Nivel de confianza
                item {
                    ConfidenceCard(probability = result.probability)
                }

                // Soluciones paso a paso
                if (result.solutions.isNotEmpty()) {
                    item {
                        Text(
                            text = "Soluciones paso a paso:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    itemsIndexed(result.solutions) { index, solution ->
                        SolutionStepCard(
                            solution = solution,
                            stepNumber = index + 1,
                            isCompleted = completedSteps.contains(index + 1),
                            onComplete = {
                                completedSteps = completedSteps + (index + 1)
                            },
                            onUndo = {
                                completedSteps = completedSteps - (index + 1)
                            }
                        )
                    }
                }

                // Botones de acción
                item {
                    ActionButtonsSection(
                        severity = result.severity,
                        navigator = navigator
                    )
                }

                // Espaciado final
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun ResultCard(result: DiagnosticResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = result.severity.color.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Emoji de severidad
            Text(
                text = result.severity.emoji,
                fontSize = 48.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = result.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = result.description,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun ConfidenceCard(probability: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🎯",
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Nivel de confianza del diagnóstico",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(4.dp))

                LinearProgressIndicator(
                    progress = probability / 100f,
                    modifier = Modifier.fillMaxWidth(),
                    color = when {
                        probability >= 80 -> Color(0xFF4CAF50)
                        probability >= 60 -> Color(0xFFFF9800)
                        else -> Color(0xFFF44336)
                    }
                )
            }

            Text(
                text = "$probability%",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun SolutionStepCard(
    solution: Solution,
    stepNumber: Int,
    isCompleted: Boolean,
    onComplete: () -> Unit,
    onUndo: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted)
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Número del paso o check
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        if (isCompleted)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text(
                        text = stepNumber.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = solution.instruction,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (solution.isAdvanced) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⚠️",
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Paso avanzado - considera pedir ayuda",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (!isCompleted) {
                        Button(
                            onClick = onComplete,
                            modifier = Modifier.height(36.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Completado", fontSize = 12.sp)
                        }
                    } else {
                        OutlinedButton(
                            onClick = onUndo,
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text("Deshacer", fontSize = 12.sp)
                        }
                    }

                    if (solution.videoUrl != null) {
                        OutlinedButton(
                            onClick = { /* Abrir video */ },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Video", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionButtonsSection(
    severity: SeverityLevel,
    navigator: cafe.adriel.voyager.navigator.Navigator
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (severity) {
            SeverityLevel.HIGH -> {
                Button(
                    onClick = { /* Buscar técnicos cerca */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF44336)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("🔧 Buscar técnico especializado", fontSize = 15.sp)
                }
            }
            SeverityLevel.MEDIUM -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* Más información */ },
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("📚 Más info", fontSize = 14.sp)
                    }

                    Button(
                        onClick = { /* Buscar ayuda */ },
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("🆘 Pedir ayuda", fontSize = 14.sp)
                    }
                }
            }
            SeverityLevel.LOW -> {
                OutlinedButton(
                    onClick = { /* Consejos de mantenimiento */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("💡 Consejos de mantenimiento", fontSize = 15.sp)
                }
            }
        }

        // Botón para nuevo diagnóstico
        OutlinedButton(
            onClick = {
                navigator.popAll()
                navigator.push(WelcomeScreen())
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("🔄 Hacer otro diagnóstico", fontSize = 14.sp)
        }
    }
}