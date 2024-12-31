package ai.derek.michigo.view.ui.main

import ai.derek.michigo.model.Schedule
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed interface ScheduleButtonType {
    data object Highlight : ScheduleButtonType
    data object Details : ScheduleButtonType
}

@Composable
fun ScheduleScreen(
    innerPadding: PaddingValues,
    schedules: List<Schedule>,
    onClick: (ScheduleButtonType) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 10.dp)
    ) {
        items(schedules) {
            ScheduleItem(it, onClick)
        }
    }
}
