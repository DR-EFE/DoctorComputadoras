package com.primer.secomputadora.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class HelpScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("¿Cómo funciona?") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
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
                item {
                    HelpCard(
                        title = "🎯 ¿Qué hace esta app?",
                        content = "Te ayuda a descubrir qué problema puede tener tu computadora usando preguntas simples, como si fuera un doctor pero para tu equipo."
                    )
                }

                item {
                    HelpCard(
                        title = "🚦 Sistema de colores",
                        content = "🟢 Verde: Problemas fáciles de solucionar\n🟡 Amarillo: Problemas moderados\n🔴 Rojo: Necesitas ayuda profesional"
                    )
                }

                item {
                    HelpCard(
                        title = "❓ ¿Cómo usar la app?",
                        content = "1. Selecciona el problema que mejor describe tu situación\n2. Responde las preguntas simples\n3. Recibe un diagnóstico y soluciones paso a paso"
                    )
                }

                item {
                    HelpCard(
                        title = "⚠️ Importante recordar",
                        content = "Esta app es una guía inicial. Para problemas graves o si no te sientes cómodo/a, siempre es mejor consultar con un técnico especializado."
                    )
                }
            }
        }
    }
}

@Composable
fun HelpCard(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}