package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkAcademicTerm(
    @Json(name = "subjectId") val subjectId: String,
    @Json(name = "academicTermId") val academicTermId: String,
    @Json(name = "value") val value: MarkAcademicTermValue
)