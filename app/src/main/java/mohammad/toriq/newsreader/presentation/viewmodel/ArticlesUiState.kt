package mohammad.toriq.newsreader.presentation.viewmodel

import mohammad.toriq.newsreader.domain.model.Article
import mohammad.toriq.newsreader.domain.model.Articles

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
data class ArticlesUiState (
    val articlesList: ArrayList<Article> = ArrayList(),
    val isLoading: Boolean = false
)