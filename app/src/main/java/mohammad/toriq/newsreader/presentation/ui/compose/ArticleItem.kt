package mohammad.toriq.newsreader.presentation.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import mohammad.toriq.newsreader.domain.model.Article
import mohammad.toriq.newsreader.R
import mohammad.toriq.newsreader.domain.model.Source
import mohammad.toriq.newsreader.util.Constants
import mohammad.toriq.newsreader.util.Util

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
@Composable
fun ArticleItem(
    article: Article,
    onClick:()->Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clickable(
                onClick = onClick
            ),
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = article.title!!,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            modifier = Modifier,
        )
        if(article.publishedAt!=null){
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Publish : "+changeFormatDate(article.publishedAt),
                fontSize = 12.sp,
                color = colorResource(id = R.color.grey),
                modifier = Modifier.align(Alignment.End),
            )
        }
        if(article.urlToImage!=null){
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = article.description?: article.content?: "",
            fontSize = 12.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier.align(Alignment.End),
        )
        if(article.author!=null){
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Author : "+article.author,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.End),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Show Article >>",
            fontSize = 12.sp,
            color = colorResource(id = R.color.primary),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.End),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = colorResource(id = R.color.grey), thickness = 0.5.dp)
    }
}

fun changeFormatDate(publishedAt: String) : String{
    var inFormat = Constants.DATE_OUT_FORMAT_DEF0
    if(publishedAt.contains(".")){
        inFormat = Constants.DATE_OUT_FORMAT_DEF1
    }
    return Util.convertDate(publishedAt, inFormat, Constants.DATE_OUT_FORMAT_DEF3)
}

//@Preview
//@Composable
//fun preve(){
//    ArticleItem(
//        article = Article(
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
//        ),
//        onClick = fun(){}
//    )
//}