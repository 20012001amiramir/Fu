package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.subject.Subject

@JsonClass(generateAdapter = true)
data class SubjectYearMark(
    @Json(name = "subject") val subject: Subject,
    @Json(name = "academicTerms") val academicTerms: List<AcademicTermWithMark>
)