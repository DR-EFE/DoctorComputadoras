package com.primer.secomputadora.models


data class Symptom(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val severity: SeverityLevel
)

data class Question(
    val id: String,
    val text: String,
    val type: QuestionType,
    val options: List<String> = emptyList(),
    val imageResource: Int? = null
)

data class DiagnosticResult(
    val title: String,
    val description: String,
    val severity: SeverityLevel,
    val solutions: List<Solution>,
    val probability: Int
)

data class Solution(
    val step: Int,
    val instruction: String,
    val isAdvanced: Boolean = false,
    val videoUrl: String? = null,
    val imageResource: Int? = null
)

enum class SeverityLevel(val color: androidx.compose.ui.graphics.Color, val emoji: String) {
    LOW(androidx.compose.ui.graphics.Color.Green, "🟢"),
    MEDIUM(androidx.compose.ui.graphics.Color(0xFFFF9800), "🟡"),
    HIGH(androidx.compose.ui.graphics.Color.Red, "🔴")
}

enum class QuestionType {
    YES_NO,
    MULTIPLE_CHOICE,
    VISUAL_SELECTION
}
