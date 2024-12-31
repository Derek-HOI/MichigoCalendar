package ai.derek.michigo.model

import androidx.compose.ui.graphics.Color

data class TeamInfo(
    val name: String,
    val logo: String?,
    val score: Int,
    val scoreColor: Color
)
