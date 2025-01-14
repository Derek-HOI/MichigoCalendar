package ai.derek.michigo.view.ui.main

import ai.derek.michigo.R
import ai.derek.michigo.model.GameState
import ai.derek.michigo.model.Schedule
import ai.derek.michigo.model.TeamInfo
import ai.derek.michigo.view.ext.MichigoPreview
import ai.derek.michigo.view.ext.bounceClick
import ai.derek.michigo.view.ui.theme.MichigoColor
import ai.derek.michigo.view.ui.theme.PersianBlue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.OffsetDateTime

@Composable
fun ScheduleItem(
    schedule: Schedule,
    onClick: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = MichigoColor, shape = RoundedCornerShape(6.dp))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = schedule.matchDate,
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Text(
            text = schedule.leagueInfo,
            style = TextStyle(
                color = PersianBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Text(
            text = schedule.gameState.text,
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .background(
                    color = schedule.gameState.color,
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TeamItem(
                modifier = Modifier.weight(1.0F),
                item = schedule.leftTeam
            )
            Image(
                painter = painterResource(R.drawable.ic_versus_24),
                contentDescription = null
            )
            TeamItem(
                modifier = Modifier.weight(1.0F),
                item = schedule.rightTeam
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            LinkButton(
                modifier = Modifier.weight(1.0F),
                type = 0,
                enabled = !schedule.highlight.isNullOrBlank(),
            ) {
                schedule.highlight?.let {
                    onClick(it)
                }
            }
            LinkButton(
                modifier = Modifier.weight(1.0F),
                type = 1,
                enabled = !schedule.details.isNullOrBlank(),
            ) {
                schedule.details?.let {
                    onClick(it)
                }
            }
        }
    }
}

@Composable
private fun LinkButton(
    modifier: Modifier,
    type: Int,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.then(
            Modifier
                .bounceClick()
                .height(40.dp)
        ),
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0x1FCCCCCC),
        )
    ) {
        Box(
            modifier = Modifier
                .weight(1.0F)
                .fillMaxHeight()
                .background(
                    brush = Brush.verticalGradient(listOf(Color.White, Color.LightGray)),
                    shape = RoundedCornerShape(25.dp),
                    alpha = 0.5F
                )
        ) {
            Text(
                text = if (type == 0) "하이라이트" else "경기 결과 상세 보기",
                style = TextStyle(
                    color = if (enabled) Color.DarkGray else Color.LightGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier.align(Alignment.Center)
            )

        }
    }
}

@MichigoPreview
@Composable
private fun ScheduleItemPreview() {
    ScheduleItem(
        Schedule(
            date = OffsetDateTime.now(),
            matchDate = "2024년 10월 19일, 14:00, 소망야구장",
            leagueInfo = "소망주말리그 - 소망토요3부",
            leftTeam = TeamInfo(
                name = "다이몬즈",
                logo = null,
                score = 10,
                scoreColor = Color.Blue
            ),
            rightTeam = TeamInfo(
                name = "78꽐라스",
                logo = null,
                score = 9,
                scoreColor = Color.Red
            ),
            gameState = GameState.FINISHED,
            highlight = null,
            details = "blahblah"
        )
    ) {}
}
