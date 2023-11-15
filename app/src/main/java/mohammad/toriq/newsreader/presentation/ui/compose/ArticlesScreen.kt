@file:OptIn(ExperimentalMaterial3Api::class)

package mohammad.toriq.newsreader.presentation.ui.compose

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import mohammad.toriq.newsreader.R
import mohammad.toriq.newsreader.domain.model.Article
import mohammad.toriq.newsreader.domain.model.Source
import mohammad.toriq.newsreader.presentation.viewmodel.ArticlesViewModel
import mohammad.toriq.newsreader.presentation.viewmodel.SplashViewModel
import mohammad.toriq.newsreader.util.Screen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.toLowerCase
import mohammad.toriq.newsreader.util.Constants

/***
 * Created By Mohammad Toriq on 14/11/2023
 */

//var count = 0
@ExperimentalComposeUiApi
@Composable
fun ArticlesScreen (
    activity: ComponentActivity,
    navController: NavController,
    articlesViewModel: ArticlesViewModel = hiltViewModel()
){
    initArticles(navController,articlesViewModel)
}

//@Preview
//@Composable
//fun Preview(){
//    initArticles(arrayListOf())
//    initArticles(arrayListOf(
//        Article(
//            source = Source(
//                id = "the-washington-post",
//                name = "The Washington Post"
//            ),
//            author = "Kelly Kasulis Cho, Rachel Pannett, Leo Sands, Naomi Schanen, Frances Vinall, Sammy Westfall, Missy Khamvongsa, Maegan Vazquez, Karen DeYoung, Michael Birnbaum, Shira Rubin, Sarah Dadouch, Miriam Berger, Hazem Balousha",
//            title = "Israel-Hamas war latest news: Fighting rages around Gaza’s hospitals - The Washington Post",
//            description = "President Biden expressed concern over the situation at al-Shifa, Gaza’s largest hospital, as options for medical care in the enclave rapidly diminish.",
//            url = "https://www.washingtonpost.com/world/2023/11/14/israel-hamas-war-gaza-palestine-live/",
//            urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://d1i4t8bqe7zgj6.cloudfront.net/11-14-2023/t_b078dc45f760483b912ee75605f3a712_name_RV33KNCGDPY2E56M3Q5R4V5CBE.jpg&w=1440",
//            publishedAt="2023-11-14T10:05:01Z",
//            content = "Israel-Gaza war\\r\\nGazas largest hospital, al-Shifa, is not functioning after days without power, water or reliable internet, the World Health Organization said. Understand whats behind the Israel-Gaza… [+847 chars]"
//        )
//    ))
//}

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun initArticles(
    navController: NavController,
    articlesViewModel: ArticlesViewModel
) {
    val focusManger = LocalFocusManager.current
    val lazyListState = rememberLazyListState()
    val state = articlesViewModel.state.value
    var isRefreshing = articlesViewModel.isRefresh
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Surface(
                shadowElevation = 3.dp
            ){
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = colorResource(id = R.color.primary),
                        titleContentColor = colorResource(id = R.color.white),
                    ),
                    title = {
                        Text(
                            text = "Top Headlines",
                            fontWeight = FontWeight.Bold
                        )
                    },
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .padding(5.dp)
                    .fillMaxSize()) {

                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = {
                        articlesViewModel.refresh()
                    }
                ) {
                    LazyColumn (
                        modifier = Modifier
                            .pointerInteropFilter {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        focusManger.clearFocus()
                                    }
                                    MotionEvent.ACTION_UP -> {
                                        focusManger.clearFocus()
                                    }
                                }
                                false
                            },
                        state = lazyListState
                    )
                    {
                        item {
                            if (articlesViewModel.showSearch) {
                                Column (
                                    modifier = Modifier.padding(5.dp)
                                ){
                                    var text by remember { mutableStateOf(TextFieldValue(articlesViewModel.search?:"")) }
                                    BasicTextField(
                                        value = text,
                                        onValueChange = { newText ->
                                            text = newText
                                            if(newText.text.length == 0){
                                                articlesViewModel.search = null
                                                articlesViewModel.refresh()
                                            }
                                        },
                                        singleLine = true,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = colorResource(id = R.color.white),
                                            ),
                                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                        keyboardActions = KeyboardActions(
                                            onSearch = {
                                                articlesViewModel.search = text.text
                                                articlesViewModel.refresh()
                                                keyboardController?.hide()
                                            }
                                        ),
                                        decorationBox = { innerTextField ->
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .border(
                                                        width = 1.dp,
                                                        color = colorResource(id = R.color.black),
                                                        shape = RoundedCornerShape(6.dp)
                                                    )

                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(10.dp),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Search,
                                                        contentDescription = "search",
                                                    )
                                                    Box(modifier = Modifier.weight(1f).padding(horizontal = 5.dp)){
                                                        if(text.text.isEmpty())
                                                            Text(text = "Searching",
                                                                color = colorResource(id = R.color.grey),
                                                                fontSize = 14.sp,)
                                                        innerTextField()
                                                    }
                                                    if(text.text.length > 0){
                                                        Icon(
                                                            imageVector = Icons.Default.Close,
                                                            contentDescription = "closeIcon",
                                                            modifier = Modifier.clickable {
                                                                var reset by mutableStateOf(TextFieldValue(""))
                                                                text = reset
                                                                articlesViewModel.search = null
                                                                articlesViewModel.refresh()
                                                            }
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Row {
                                        Box(modifier = Modifier.weight(1f)) {
                                            var list = Constants.getCategories()
                                            var selectedIndex = 2
                                            list.forEachIndexed { index, s ->
                                                if(articlesViewModel.category.equals(s.lowercase())){
                                                    selectedIndex = index
                                                }
                                            }
                                            DropdownMenuTitle(
                                                title = "Category",
                                                options = list,
                                                onClick = fun(index : Int){
                                                    Log.d("OkCheck","Category onClick")
                                                    articlesViewModel.category =
                                                        list[index].lowercase()
                                                    articlesViewModel.refresh()
                                                },
                                                selectedIndex = selectedIndex,
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Box(modifier = Modifier.weight(1f)) {
                                            var list = Constants.getLanguages()
                                            var value = Constants.getLanguageCodes()
                                            var selectedIndex = 2
                                            value.forEachIndexed { index, s ->
                                                if(articlesViewModel.language.equals(s)){
                                                    selectedIndex = index
                                                }
                                            }
                                            DropdownMenuTitle(
                                                title = "Language",
                                                options = list,
                                                onClick = fun(index : Int){
                                                    Log.d("OkCheck","Language onClick")
                                                    articlesViewModel.language =
                                                        value[index].lowercase()
                                                    articlesViewModel.refresh()
                                              },
                                                selectedIndex = selectedIndex
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        if(state.articlesList.size > 0){
                            items(state.articlesList.toList()){ item ->
                                ArticleItem(
                                    article = item,
                                    onClick = fun (){
                                        navController.navigate(
                                            Screen.DetailArticleScreen
                                                .sendData(item.url!!+"&"+item.title!!)
                                        )
                                    }
                                )
                            }
                        }else{
                            if (!state.isLoading) {
                                item {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        Text(
                                            text = "Article not Found",
                                            color = colorResource(id = R.color.grey),
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            if (state.isLoading) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                    InfiniteListHandler(lazyListState = lazyListState) {
                        if(!articlesViewModel.isMax){
                            articlesViewModel.getArticles()
                        }
                    }
                }
            }
        }
    )
}