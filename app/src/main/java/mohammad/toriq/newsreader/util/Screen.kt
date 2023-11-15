package mohammad.toriq.newsreader.util

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object ArticlesScreen: Screen("articles_screen")
    object DetailArticleScreen: Screen("detail_article_screen?{url_title}"){
        fun sendData(url_title: String) = "detail_article_screen?$url_title"
    }
}