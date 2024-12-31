package ai.derek.michigo.network.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface GameScheduleApi {

    @FormUrlEncoded
    @POST("teamPage/scheduleRecord/getGameSchedule.hs")
    suspend fun getGameSchedule(
        @Query("teamSeq")
        teamSeq: Int,
        @Field("thisYear")
        thisYear: Int,
        @Field("thisMonth")
        thisMonth: Int,
        @Field("thisDay")
        thisDay: Int
    ): Response<ResponseBody>
}
