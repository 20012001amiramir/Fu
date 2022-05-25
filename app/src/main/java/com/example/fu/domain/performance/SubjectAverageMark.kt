package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.subject.Subject

@JsonClass(generateAdapter = true)
data class SubjectAverageMark(
    @Json(name = "subject") val subject: Subject,
    @Json(name = "markAcademicTerm") val markAcademicTerm: MarkAcademicTerm?,
    @Json(name = "markAverage") val markAverage: MarkAverage?
)