package ai.derek.michigo.view.ui.main

import ai.derek.michigo.model.TeamInfo
import ai.derek.michigo.util.isMyTeam
import ai.derek.michigo.view.ext.MichigoPreview
import ai.derek.michigo.view.ui.theme.MichigoColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamItem(
    modifier: Modifier,
    item: TeamInfo
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // team logo
        GlideImage(
            model = item.logo,
            contentDescription = "logo",
            modifier = Modifier.size(60.dp)
        )
        // team name
        Text(
            text = item.name,
            style = TextStyle(
                color = if (item.name.isMyTeam()) MichigoColor else Color.Gray,
                fontSize = 14.sp,
                fontWeight = if (item.name.isMyTeam()) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        )
        // score
        Text(
            text = item.score.toString(),
            style = TextStyle(
                color = item.scoreColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
    }
}

@MichigoPreview
@Composable
private fun TeamItemPreview() {
    TeamItem(
        modifier = Modifier
            .width(125.dp)
            .padding(8.dp),
        TeamInfo(
            name = "미치고",
            logo = null,
            score = 10,
            scoreColor = Color.Red
        )
    )
}
