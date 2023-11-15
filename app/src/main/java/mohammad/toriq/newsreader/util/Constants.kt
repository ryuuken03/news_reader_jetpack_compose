package mohammad.toriq.newsreader.util

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
class Constants {
    companion object{

        val IS_LOG: Boolean = getIsLog()

        val CONNECT_TIMEOUT : Long = 60
        val READ_TIMEOUT: Long = 60
        val WRITE_TIMEOUT: Long = 60
        val IMG_CONNECT_TIMEOUT : Long = 10000
        val IMG_READ_TIMEOUT: Long = 10000
        val IMG_WRITE_TIMEOUT: Long = 10000

        val DATE_OUT_FORMAT_DEF0 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val DATE_OUT_FORMAT_DEF1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val DATE_OUT_FORMAT_DEF2 = "yyyy-MM-dd"
        val DATE_OUT_FORMAT_DEF3 = "MMM dd, yyyy"

        fun getIsLog(): Boolean {
            return true
//            return  if (!BuildConfig.DEBUG) {
//                false
//            } else {
//                true
//            }
        }
        fun getCategories() : List<String>{
            return listOf(
                "Business",
                "Entertainment",
                "General",
                "Health",
                "Science",
                "Sports",
                "Technology")
        }
        fun getLanguageCodes() : List<String>{
            return listOf(
                "ar",
                "de",
                "en",
                "es",
                "fr",
                "he",
                "it",
                "nl",
                "no",
                "pt",
                "ru",
                "sv",
                "zh")
        }
        fun getLanguages() : List<String>{
            return listOf(
                "Arabic",
                "German",
                "English",
                "Spanish",
                "French",
                "Hebrew",
                "Italian",
                "Dutch",
                "Norwegian",
                "Portuguese",
                "Russian",
                "Swedish",
                "Chinese")
        }
    }
}