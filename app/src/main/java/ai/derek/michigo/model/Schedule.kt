package ai.derek.michigo.model

import java.time.OffsetDateTime

data class Schedule(
    val date: OffsetDateTime,
    // yyyy년 MM월 dd일\nHH:mm\n소망야구장
    val matchDate: String,
    // 소망주말리그  -  소망토요#부
    val leagueInfo: String,
    val leftTeam: TeamInfo,
    val rightTeam: TeamInfo,
    val gameState: GameState,
    val highlight: String?,
    val details: String?,
)
