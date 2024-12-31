package ai.derek.michigo.di.module

import ai.derek.michigo.BASE_URL
import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().disableHtmlEscaping().create()

        return Retrofit.Builder().client(
            OkHttpClient.Builder().apply {
                addInterceptor(
                    HttpLoggingInterceptor {
                        Log.d("Okhttp", "it= $it")
                    }.apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }.build()
        )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
    }

}
