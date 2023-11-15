package mohammad.toriq.newsreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mohammad.toriq.newsreader.presentation.ui.compose.ArticlesScreen
import mohammad.toriq.newsreader.presentation.ui.compose.SplashScreen
import mohammad.toriq.newsreader.presentation.ui.compose.DetailArticleScreen
import mohammad.toriq.newsreader.ui.theme.NewsReaderTheme
import mohammad.toriq.newsreader.util.Screen

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PageScreen()
        }
    }


    @Composable
    fun PageScreen(){
        NewsReaderTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SplashScreen.route
                ) {
                    composable(route = Screen.SplashScreen.route) {
                        SplashScreen(this@MainActivity,navController)
                    }
                    composable(route = Screen.ArticlesScreen.route) {
                        ArticlesScreen(this@MainActivity,navController)
                    }
                    composable(route = Screen.DetailArticleScreen.route){
                        var url_title = it.arguments?.getString("url_title") ?: ""
                        DetailArticleScreen(this@MainActivity,navController,url_title)
                    }
                }
            }
        }
    }
}