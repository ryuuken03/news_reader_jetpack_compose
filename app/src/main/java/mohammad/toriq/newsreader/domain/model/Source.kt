package mohammad.toriq.newsreader.domain.model

import mohammad.toriq.newsreader.data.source.remote.dto.SourceDto

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
data class Source (
    val id :String?,
    val name :String?,
)

fun Source.toSourceDto(): SourceDto{
    return SourceDto(
        id = id,
        name = name,
    )
}