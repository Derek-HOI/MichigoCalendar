package ai.derek.michigo.view.ui.btn

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyknowk.presentation.ext.clickableSingle

@Composable
fun AnimatedGradientButton(
    enabled: Boolean,
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    val transition = rememberInfiniteTransition(label = "")
    val gradientOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box(
        modifier = modifier.then(
            Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Blue, Color.Green),
                        start = androidx.compose.ui.geometry.Offset(gradientOffset, 0f),
                        end = androidx.compose.ui.geometry.Offset(gradientOffset + 500f, 0f)
                    ),
                    shape = RoundedCornerShape(99.dp)
                )
                .padding(vertical = 8.dp)
                .clickableSingle(onClick = onClick)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = if (enabled) Color.DarkGray else Color.LightGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}
