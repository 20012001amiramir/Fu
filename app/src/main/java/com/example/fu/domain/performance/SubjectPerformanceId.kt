package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectPerformanceId(
    @Json(name = "academicTermId") val academicTermId: String,
    @Json(name = "subjectId") val subjectId: String
)