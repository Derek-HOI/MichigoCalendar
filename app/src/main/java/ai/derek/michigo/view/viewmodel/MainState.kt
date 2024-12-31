package ai.derek.michigo.view.viewmodel

import ai.derek.michigo.model.Schedule
import ai.derek.michigo.view.ui.state.UiStatus
import java.time.YearMonth

data class MainState(
    val uiStatus: UiStatus = UiStatus.Success,
    val yearMonth: YearMonth = YearMonth.now(),
    val schedules: List<Schedule> = emptyList()
)
