package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkExam(
    @Json(name = "subjectId") val subjectId: String,
    @Json(name = "value") val value: MarkExamValue
)