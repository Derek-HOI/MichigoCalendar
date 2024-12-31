package ai.derek.michigo.di.module

import ai.derek.michigo.network.api.GameScheduleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import kotlin.jvm.java

// provide function
inline fun <reified I> provideApi(retrofit: Retrofit): I = retrofit.create(I::class.java)

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    fun provideGameScheduleApi(retrofit: Retrofit): GameScheduleApi =
        provideApi(retrofit)
}
