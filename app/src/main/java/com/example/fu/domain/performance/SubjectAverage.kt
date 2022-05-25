package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.subject.Subject

@JsonClass(generateAdapter = true)
data class SubjectAverage(
    @Json(name = "perfomanceId") val perfomanceId: String?,
    @Json(name = "marksCount") val marksCount: Int?,
    @Json(name = "markAverage") val markAverage: MarkAverage?
)