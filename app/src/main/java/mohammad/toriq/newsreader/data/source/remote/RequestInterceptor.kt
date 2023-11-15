package mohammad.toriq.newsreader.data.source.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import mohammad.toriq.newsreader.R

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url
            .newBuilder()
            .addQueryParameter(
                "apiKey",
                "78782dce784c49c788e5fb1af8c40869"
//                "391c297e46604896bcd6ada344e7a48a"
//                "270ab5ea3209412d8761e1d83c8ad563"
            )
            .build()
        val request = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}