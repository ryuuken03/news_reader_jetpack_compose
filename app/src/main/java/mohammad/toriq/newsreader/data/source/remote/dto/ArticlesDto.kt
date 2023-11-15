package mohammad.toriq.newsreader.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import mohammad.toriq.newsreader.data.source.remote.dto.SourceDto
import mohammad.toriq.newsreader.domain.model.Article
import mohammad.toriq.newsreader.domain.model.Articles
import mohammad.toriq.newsreader.domain.model.Source
import mohammad.toriq.newsreader.domain.model.toArticleDto

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
class ArticlesDto (
    @SerializedName("articles")
    val articles :List<ArticleDto>,
    @SerializedName("status")
    val status :String?,
    @SerializedName("totalResults")
    val totalResults :Int = 0,
)

fun ArticlesDto.toArticles(): Articles {
    return Articles(
        articles = articles.map { it.toArticle() }.toList(),
        status = status,
        totalResults = totalResults,
    )
}

//fun ArticlesDto.toArticles(): List<Article>{
//    return articles.map { it.toArticle() }.toList()
//}