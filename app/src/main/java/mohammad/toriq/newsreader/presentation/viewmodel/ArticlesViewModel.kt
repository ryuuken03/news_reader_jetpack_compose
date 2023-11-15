package mohammad.toriq.newsreader.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mohammad.toriq.newsreader.domain.usecase.GetArticleTopHeadlines
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mohammad.toriq.newsreader.data.source.remote.Resource
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticleTopHeadlines: GetArticleTopHeadlines
) : ViewModel()  {

    private val _state = mutableStateOf(ArticlesUiState())
    val state: State<ArticlesUiState> = _state

    var page = 1
    var showSearch = false
    var search : String? = null
    var remainingArticle = 0
    var isRefresh = false
    var isMax = false
    var category = "general"
    var language = "en"

    fun getArticles() {
        viewModelScope.launch {
            getArticleTopHeadlines(search,category,language,page).onEach { result ->
                var list = state.value.articlesList
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            articlesList = list,
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        if(page == 1){
                            remainingArticle = result.data?.totalResults!!
                        }
                        if(result.data?.articles !=null){
                            result.data.articles.forEach {
                                list.add(it)
                            }
                            remainingArticle = remainingArticle - result.data.articles.size
                        }
                        Log.d("OkCheck","getArticles remainingArticle:"+remainingArticle.toString())
                        if(remainingArticle > 0){
                            page++
                        }else{
                            isMax = true
                        }
                        _state.value = state.value.copy(
                            articlesList = list,
                            isLoading = false
                        )
                        showSearch = true
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            articlesList = list,
                            isLoading = false
                        )
                        showSearch = true

                    }
                }
            }.launchIn(this)
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            isRefresh = true
            isMax = false
            showSearch = false
            page = 1
            var isLoad = false
            Log.d("OkCheck","state.value.articlesList.size = "+state.value.articlesList.size.toString())
            if(state.value.articlesList.size < 3){
                isLoad = true
            }
            _state.value = state.value.copy(
                articlesList = ArrayList(),
                isLoading = false
            )
            remainingArticle = 0
            if(isLoad){
                Log.d("OkCheck","load")
                getArticles()
            }
            showSearch = true
            isRefresh = false
        }

    }
}