package com.primer.secomputadora.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.draw.rotate
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
import com.google.rpc.Help
import kotlinx.coroutines.delay

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        // Estados de animación
        var logoVisible by remember { mutableStateOf(false) }
        var titleVisible by remember { mutableStateOf(false) }
        var descriptionVisible by remember { mutableStateOf(false) }
        var buttonsVisible by remember { mutableStateOf(false) }
        var floatingElementsVisible by remember { mutableStateOf(false) }

        // Secuencia de animaciones
        LaunchedEffect(Unit) {
            logoVisible = true
            delay(400)
            titleVisible = true
            delay(300)
            descriptionVisible = true
            delay(400)
            buttonsVisible = true
            delay(500)
            floatingElementsVisible = true
        }

        // Gradiente de fondo animado
        val infiniteTransition = rememberInfiniteTransition()
        val gradientOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val dynamicColors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f + gradientOffset * 0.05f),
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.08f + gradientOffset * 0.03f),
            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f + gradientOffset * 0.02f),
            MaterialTheme.colorScheme.surface
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = dynamicColors,
                        center = androidx.compose.ui.geometry.Offset(
                            0.3f + gradientOffset * 0.4f,
                            0.2f + gradientOffset * 0.3f
                        )
                    )
                )
        ) {
            // Elementos flotantes decorativos
            FloatingElements(
                visible = floatingElementsVisible,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo animado
                AnimatedVisibility(
                    visible = logoVisible,
                    enter = scaleIn(
                        initialScale = 0.3f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ) + fadeIn(animationSpec = tween(800))
                ) {
                    AnimatedLogo()
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Título con animación
                AnimatedVisibility(
                    visible = titleVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it / 2 },
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(800))
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Doctor",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Computadoras",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Descripción con animación
                AnimatedVisibility(
                    visible = descriptionVisible,
                    enter = slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(1000, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(1000))
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🔧 ⚡ 🛠️",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Te ayudo a descubrir qué le pasa a tu computadora de manera sencilla y profesional",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Botones con animación
                AnimatedVisibility(
                    visible = buttonsVisible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(1000, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(1000))
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Botón principal
                        AnimatedButton(
                            onClick = { navigator.push(SymptomSelectionScreen()) },
                            isPrimary = true,
                            icon = Icons.Default.PlayArrow,
                            text = "Diagnosticar mi computadora",
                            description = "Empezar diagnóstico"
                        )

                        // Botón secundario
                        AnimatedButton(
                            onClick = { navigator.push(HelpScreen()) },
                            isPrimary = false,
                       icon = Icons.Default.Info,
                            text = "¿Cómo funciona?",
                            description = "Conocer más"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedLogo() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier.size(140.dp),
        contentAlignment = Alignment.Center
    ) {
        // Anillo exterior rotante
        Box(
            modifier = Modifier
                .size(140.dp)
                .rotate(rotation)
                .clip(CircleShape)
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                        )
                    )
                )
        )

        // Logo principal
        Card(
            modifier = Modifier
                .size(120.dp)
                .scale(pulse),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "💻",
                    fontSize = 64.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedButton(
    onClick: () -> Unit,
    isPrimary: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    description: String
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isPrimary) 72.dp else 60.dp)
            .scale(scale),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPrimary)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPrimary) 8.dp else 4.dp,
            pressedElevation = if (isPrimary) 4.dp else 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isPrimary)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = text,
                    fontSize = if (isPrimary) 18.sp else 16.sp,
                    fontWeight = if (isPrimary) FontWeight.Bold else FontWeight.Medium,
                    color = if (isPrimary)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSurface
                )

                if (isPrimary) {
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingElements(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val float1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val float2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotate by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(1000)),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Elemento flotante 1
            Box(
                modifier = Modifier
                    .offset(x = 50.dp, y = 100.dp + float1.dp)
                    .size(40.dp)
                    .rotate(rotate)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        )
                )
            }

            // Elemento flotante 2
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-30).dp, y = 150.dp + float2.dp)
                    .size(30.dp)
                    .rotate(-rotate)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                    )
            )

            // Elemento flotante 3
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 80.dp, y = (-200).dp + float1.dp)
                    .size(25.dp)
                    .rotate(rotate * 0.5f)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f)
                    )
            )
        }
    }
}