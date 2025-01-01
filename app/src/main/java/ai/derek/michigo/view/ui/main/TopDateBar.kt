package ai.derek.michigo.view.ui.main

import ai.derek.michigo.R
import ai.derek.michigo.view.ext.MichigoPreview
import ai.derek.michigo.view.ext.bounceClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.YearMonth

sealed interface DateButtonType {
    data object PrevMonth : DateButtonType
    data object NextMonth : DateButtonType
}

@Composable
fun TopDateBar(yearMonth: YearMonth, onClick: (DateButtonType) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    onClick(DateButtonType.PrevMonth)
                },
                modifier = Modifier.bounceClick(),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.round_arrow_back_24),
                    contentDescription = "",
                )
            }
            Text(
                text = "%d년 %02d월".format(yearMonth.year, yearMonth.monthValue),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
            Button(
                onClick = {
                    onClick(DateButtonType.NextMonth)
                },
                modifier = Modifier.bounceClick(),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.round_arrow_forward_24),
                    contentDescription = "",
                )
            }
        }
    }
}

@MichigoPreview
@Composable
private fun TopDateItemPreview() {
    TopDateBar(yearMonth = YearMonth.now()) { }
}
