package ru.tstst.schoolboy.domain.story

import com.squareup.moshi.Json
import java.io.Serializable

data class BackgroudEntity(
    @Json(name = "type") val type: TypeBackgroundEnum = TypeBackgroundEnum.MEDIA,
    @Json(name = "payload") val payload: MediaEntity
) : Serializable
enum class TypeBackgroundEnum {
    MEDIA, GRADIENT
}