package com.primer.secomputadora.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.primer.secomputadora.models.DiagnosticEngine
import com.primer.secomputadora.models.Symptom
import kotlinx.coroutines.delay

class SymptomSelectionScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val diagnosticEngine = remember { DiagnosticEngine() }
        val symptoms = remember { diagnosticEngine.getSymptoms() }
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        // Animación de entrada para el título
        var titleVisible by remember { mutableStateOf(false) }
        var subtitleVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            titleVisible = true
            delay(300)
            subtitleVisible = true
        }

        // Gradiente de fondo dinámico
        val gradientColors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.05f),
            MaterialTheme.colorScheme.surface
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        AnimatedVisibility(
                            visible = titleVisible,
                            enter = slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(600, easing = FastOutSlowInEasing)
                            ) + fadeIn(animationSpec = tween(600))
                        ) {
                            Text(
                                "¿Qué problema tienes?",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator.pop() },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        ) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Volver",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(gradientColors)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))

                        // Título con animación mejorada
                        AnimatedVisibility(
                            visible = subtitleVisible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(800, easing = FastOutSlowInEasing)
                            ) + fadeIn(animationSpec = tween(800))
                        ) {
                            Column {
                                Text(
                                    text = "🩺",
                                    fontSize = 48.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Selecciona lo que mejor describe tu situación",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                    lineHeight = 24.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Toca una opción para continuar",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    itemsIndexed(symptoms) { index, symptom ->
                        AnimatedSymptomCard(
                            symptom = symptom,
                            index = index,
                            onClick = {
                                navigator.push(QuestionnaireScreen(symptom.id))
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }

                // Indicador flotante de ayuda
                FloatingHelpIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedSymptomCard(
    symptom: Symptom,
    index: Int,
    onClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }

    // Animación de entrada escalonada
    LaunchedEffect(Unit) {
        delay((index * 150).toLong())
        isVisible = true
    }

    // Animación de escala para el press
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // Animación de entrada
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { if (index % 2 == 0) -it else it },
            animationSpec = tween(600, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(600)) + scaleIn(
            initialScale = 0.8f,
            animationSpec = tween(600, easing = FastOutSlowInEasing)
        )
    ) {
        Card(
            onClick = {
                onClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .scale(scale)
                .graphicsLayer {
                    shadowElevation = if (isPressed) 1.dp.toPx() else 8.dp.toPx()
                },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Contenedor del emoji con animación
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = symptom.severity.emoji,
                        fontSize = 28.sp
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = symptom.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = symptom.description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        lineHeight = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Indicador de acción con animación
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingHelpIndicator(
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy
            )
        ) + fadeIn(),
        modifier = modifier
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        val pulse by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.scale(pulse)
        ) {
            Text(
                text = "💡",
                fontSize = 24.sp,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}