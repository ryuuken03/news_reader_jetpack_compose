@file:OptIn(ExperimentalMaterial3Api::class)

package mohammad.toriq.newsreader.presentation.ui.compose

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mohammad.toriq.newsreader.R
import mohammad.toriq.newsreader.presentation.viewmodel.SplashViewModel
import mohammad.toriq.newsreader.util.Screen

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
@Composable
fun SplashScreen (
    activity: ComponentActivity,
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
){
    val state = splashViewModel.state.value
    if(!state.isLoading){
//        Toast.makeText(activity,"ArticleScreen",Toast.LENGTH_SHORT).show()
        splashViewModel.setState(true)
        Log.d("OkCheck","ArticleScreen")
        navController.navigate(Screen.ArticlesScreen.route){
            popUpTo(Screen.SplashScreen.route){
                inclusive = true
            }
        }
    }
    initSplash()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun initSplash(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box() {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment  = Alignment.CenterHorizontally,
            )
            {

                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    modifier = modifier,
                )
            }
        }
    }
}