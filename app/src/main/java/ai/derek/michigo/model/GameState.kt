package ai.derek.michigo.model

import ai.derek.michigo.view.ui.theme.PersianBlue
import ai.derek.michigo.view.ui.theme.SpeechRed
import androidx.compose.ui.graphics.Color

enum class GameState(val color: Color, val text: String) {
    UPCOMING(color = Color.LightGray, text = "경기 전"),
    ON_GOING(color = PersianBlue, text = "경기 중"),
    FINISHED(color = SpeechRed, text = "경기 종료"),
}
