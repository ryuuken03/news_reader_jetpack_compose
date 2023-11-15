package mohammad.toriq.newsreader.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mohammad.toriq.newsreader.data.source.remote.Resource
import mohammad.toriq.newsreader.data.source.remote.dto.ArticleAPIService
import mohammad.toriq.newsreader.data.source.remote.dto.toArticles
import mohammad.toriq.newsreader.domain.model.Article
import mohammad.toriq.newsreader.domain.model.Articles
import mohammad.toriq.newsreader.domain.repository.ArticleRepository
import retrofit2.HttpException
import java.io.IOException

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
class ArticleRepositoryImpl constructor(
    private val articleApiService: ArticleAPIService
) : ArticleRepository
{
    override fun getArticleTopHeadlines(search : String?,
                                        category:String,
                                        language:String,
                                        page:Int
    ):Flow<Resource<Articles>> = flow {
        emit(Resource.Loading())
        try {
            // single source of truth we will emit data from db only and not directly from remote
            emit(Resource.Success(getArticleTopHeadlinesFromAPI(search,category,language,page)))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }

    private suspend fun getArticleTopHeadlinesFromAPI(
        search : String?,
        category:String = "general",
        language:String = "en",
        page: Int = 1): Articles {
        val remoteArticles = articleApiService.getArticleTopHeadlines(
            q = search,
            category = category,
            language = language,
            page)
        return remoteArticles.toArticles()
    }
}