package mohammad.toriq.newsreader.data.source.remote

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}