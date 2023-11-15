package mohammad.toriq.newsreader.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import mohammad.toriq.newsreader.domain.model.Source

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
data class SourceDto (
    @SerializedName("id")
    val id :String?,
    @SerializedName("name")
    val name :String?,
)

fun SourceDto.toSource(): Source{
    return Source(
        id = id,
        name = name,
    )
}