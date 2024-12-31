package ai.derek.michigo.network.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

data class DateField(
    val thisYear: String,
    val thisMonth: String,
    val thisDay: String
)

interface GameScheduleApi {

    @POST("teamPage/scheduleRecord/getGameSchedule.hs")
    suspend fun getGameSchedule(
        @Query("teamSeq")
        teamSeq: Int,
        @Body
        body: DateField
    ): Response<ResponseBody>
}
