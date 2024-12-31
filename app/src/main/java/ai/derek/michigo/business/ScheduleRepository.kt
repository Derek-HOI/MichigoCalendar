package ai.derek.michigo.business

import ai.derek.michigo.model.Schedule
import ai.derek.michigo.model.Team
import java.time.YearMonth

interface ScheduleRepository {

    suspend fun getSchedule(
        team: Team,
        yearMonth: YearMonth
    ): List<Schedule>
}
