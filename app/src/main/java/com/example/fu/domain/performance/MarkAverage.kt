package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkAverage(
    @Json(name = "value") val value: String,
    @Json(name = "progress") val progress: MarkProgress
)