package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcademicTermWithMark(
    @Json(name = "academicTerm") val academicTerm: AcademicTerm,
    @Json(name = "markAcademicTerm") val markAcademicTerm: MarkAcademicTerm?
)