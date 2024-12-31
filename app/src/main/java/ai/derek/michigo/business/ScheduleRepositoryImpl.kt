package ai.derek.michigo.business

import ai.derek.michigo.AttrSrc
import ai.derek.michigo.DivEmptyMath
import ai.derek.michigo.DivFinishMatch
import ai.derek.michigo.DivLeagueInfo
import ai.derek.michigo.DivLeftTime
import ai.derek.michigo.DivMatchDate
import ai.derek.michigo.DivRightTime
import ai.derek.michigo.ImgTeamLogo
import ai.derek.michigo.MsgNotFound
import ai.derek.michigo.SpanResult
import ai.derek.michigo.SpanResultSep
import ai.derek.michigo.SpanScore
import ai.derek.michigo.SpanTeamName
import ai.derek.michigo.model.GameState
import ai.derek.michigo.model.Schedule
import ai.derek.michigo.model.Team
import ai.derek.michigo.model.TeamInfo
import ai.derek.michigo.network.api.DateField
import ai.derek.michigo.network.api.GameScheduleApi
import ai.derek.michigo.util.getSaturdays
import androidx.compose.ui.graphics.Color
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.time.YearMonth
import javax.inject.Inject

class ScheduleRepositoryImpl
@Inject
constructor(
    val api: GameScheduleApi
) : ScheduleRepository {

    override suspend fun getSchedule(team: Team, yearMonth: YearMonth): List<Schedule> {

        val ret = arrayListOf<Schedule>()

        getSaturdays(yearMonth).forEach {

            val result = api.getGameSchedule(
                teamSeq = team.seq,
                body = DateField(
                    thisYear = it.year.toString(),
                    thisMonth = it.monthValue.toTwoDigitString(),
                    thisDay = it.dayOfMonth.toTwoDigitString(),
                )
            )

            if (result.isSuccessful) {
                result.body()?.string()?.let { html ->
                    val document = Jsoup.parse(html)
                    if (document.select(DivEmptyMath).isNotEmpty()) {
                        // 일정 있음
                        ret.add(
                            Schedule(
                                date = it,
                                matchDate = document.getMatchDate(),
                                leagueInfo = document.getLeagueInfo(),
                                leftTeam = document.getTeam(true),
                                rightTeam = document.getTeam(false),
                                gameState = document.getGameState(),
                            )
                        )
                    }
                }
            }
        }

        return ret
    }
}

private fun Document.getMatchDate() =
    selectFirst(DivMatchDate)?.html()?.replaceBrToSpace() ?: MsgNotFound

private fun Document.getLeagueInfo() =
    selectFirst(DivLeagueInfo)?.text() ?: MsgNotFound

/**
 * left or right team
 */
private fun Document.getTeam(left: Boolean): TeamInfo {
    val element = if (left) selectFirst(DivLeftTime) else selectFirst(DivRightTime)

    return TeamInfo(
        name = element?.selectFirst(SpanTeamName)?.text() ?: MsgNotFound,
        logo = element?.selectFirst(ImgTeamLogo)?.attr(AttrSrc),
        score = element?.selectFirst(SpanScore)?.text()?.toInt() ?: 0,
        scoreColor = element?.selectFirst(SpanTeamName)?.hasClass("blue")?.let {
            if (it) Color.Blue else Color.Red
        } ?: Color.DarkGray,
    )
}

private fun Document.getGameState() =
    selectFirst(DivFinishMatch)?.let {
        it.selectFirst(SpanResult)?.hasClass(SpanResultSep)?.let {
            if (it) GameState.FINISHED else GameState.ON_GOING
        } ?: GameState.UPCOMING
    } ?: GameState.UPCOMING

private fun String.replaceBrToSpace() = replace("<br>", ", ")

fun Int.toTwoDigitString(): String = this.toString().padStart(2, '0')
