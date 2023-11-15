package mohammad.toriq.newsreader.domain.repository

import kotlinx.coroutines.flow.Flow
import mohammad.toriq.newsreader.data.source.remote.Resource
import mohammad.toriq.newsreader.domain.model.Article
import mohammad.toriq.newsreader.domain.model.Articles

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
interface ArticleRepository {
    fun getArticleTopHeadlines(search :String?,
                               category:String = "general",
                               language:String = "en",
                               page: Int = 1
    ): Flow<Resource<Articles>>
}