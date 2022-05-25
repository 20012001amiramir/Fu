package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period

@JsonClass(generateAdapter = true)
data class AcademicTerm(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: AcademicTermType,
    @Json(name = "position") val position: Int?,
    @Json(name = "tense") val tense: Tense,
    @Json(name = "period") val period: Period?
)