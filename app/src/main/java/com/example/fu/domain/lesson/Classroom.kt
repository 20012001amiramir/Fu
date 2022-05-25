package ru.tstst.schoolboy.domain.lesson

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Classroom(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)