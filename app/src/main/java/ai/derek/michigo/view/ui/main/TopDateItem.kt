package ai.derek.michigo.view.ui.main

import ai.derek.michigo.R
import ai.derek.michigo.view.ext.MichigoPreview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.hyknowk.presentation.ext.clickableSingle
import java.time.YearMonth

sealed interface DateButtonType {
    data object PrevMonth : DateButtonType
    data object NextMonth : DateButtonType
}

@Composable
fun TopDateItem(yearMonth: YearMonth, onClick: (DateButtonType) -> Unit) {

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
            Image(
                painter = painterResource(R.drawable.round_arrow_back_24),
                contentDescription = "",
                modifier = Modifier.clickableSingle {
                    onClick(DateButtonType.PrevMonth)
                }
            )
            Text(
                text = "%d년 %02d월".format(yearMonth.year, yearMonth.monthValue),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
            Image(
                painter = painterResource(R.drawable.round_arrow_forward_24),
                contentDescription = "",
                modifier = Modifier.clickableSingle {
                    onClick(DateButtonType.NextMonth)
                }
            )
        }
    }
}

@MichigoPreview
@Composable
private fun TopDateItemPreview() {
    TopDateItem(yearMonth = YearMonth.now()) { }
}
