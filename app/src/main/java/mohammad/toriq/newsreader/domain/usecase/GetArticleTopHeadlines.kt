package mohammad.toriq.newsreader.domain.usecase

import kotlinx.coroutines.flow.Flow
import mohammad.toriq.newsreader.data.source.remote.Resource
import mohammad.toriq.newsreader.domain.model.Articles
import mohammad.toriq.newsreader.domain.repository.ArticleRepository

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
class GetArticleTopHeadlines(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(search :String?,
                        category:String = "general",
                        language:String = "en",
                        page: Int = 1
    ): Flow<Resource<Articles>> {
        return articleRepository.getArticleTopHeadlines(search,category,language,page)
    }
}