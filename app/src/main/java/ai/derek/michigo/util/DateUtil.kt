package ai.derek.michigo.util

import java.time.DayOfWeek
import java.time.OffsetDateTime
import java.time.YearMonth

fun getSaturdays(yearMonth: YearMonth): List<OffsetDateTime> {
    val date = OffsetDateTime.now()

    // 달의 첫 번째 날과 마지막 날
    val firstDayOfMonth =
        date.withYear(yearMonth.year).withMonth(yearMonth.monthValue).withDayOfMonth(1).withHour(0)
            .withMinute(0).withSecond(0).withNano(0)
    val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)

    // 달의 모든 날짜 중 토요일 필터링
    return (0..lastDayOfMonth.dayOfMonth - 1)
        .map { firstDayOfMonth.plusDays(it.toLong()) }
        .filter { it.dayOfWeek == DayOfWeek.SATURDAY }
}
