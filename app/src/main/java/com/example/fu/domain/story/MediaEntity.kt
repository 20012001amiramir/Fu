package ru.tstst.schoolboy.domain.story

import com.squareup.moshi.Json
import java.io.Serializable

data class MediaEntity(
    @Json(name = "type") val type: TypeEnum = TypeEnum.Image,
    @Json(name = "preview_url") val preview_url: String,
    @Json(name = "source_url") val source_url: String,
) : Serializable
enum class TypeEnum {
    Image, Video
}