package mohammad.toriq.newsreader.di

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mohammad.toriq.newsreader.data.source.remote.RequestInterceptor
import mohammad.toriq.newsreader.data.source.remote.dto.ArticleAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 14/11/2023
 */

@Module
@InstallIn(SingletonComponent::class)
class ArticlesNetworkModule {
    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(RequestInterceptor()).addInterceptor(interceptor)
            .build()


    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): ArticleAPIService {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(ArticleAPIService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ArticleAPIService::class.java)
    }

}