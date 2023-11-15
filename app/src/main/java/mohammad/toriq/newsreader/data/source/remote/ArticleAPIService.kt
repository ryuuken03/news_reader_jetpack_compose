package mohammad.toriq.newsreader.data.source.remote.dto

import retrofit2.http.GET
import retrofit2.http.Query

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
interface ArticleAPIService {
    companion object {
        const val BASE_URL: String = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getArticleTopHeadlines(
        @Query(value = "q", encoded = true) q: String? = null,
        @Query(value = "category", encoded = true) category: String? = null,
        @Query(value = "language", encoded = true) language: String? = null,
        @Query(value = "page", encoded = true) page: Int? = null,
    ): ArticlesDto
}