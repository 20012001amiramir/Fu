package ru.tstst.schoolboy.domain.journal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Achievement(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String,
    @Json(name = "image") val image: String,
    @Json(name = "descriptionImage") val descriptionImage: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?
)