package mohammad.toriq.newsreader.domain.model

import com.google.gson.annotations.SerializedName
import mohammad.toriq.newsreader.data.source.remote.dto.ArticleDto
import mohammad.toriq.newsreader.data.source.remote.dto.ArticlesDto
import mohammad.toriq.newsreader.data.source.remote.dto.toArticle

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
data class Articles (
    val articles :List<Article>,
    val status :String?,
    val totalResults :Int = 0,
)

fun Articles.toArticlesDto(): ArticlesDto {
    return ArticlesDto(
        articles = articles.map { it.toArticleDto() }.toList(),
        status = status,
        totalResults = totalResults,
    )
}