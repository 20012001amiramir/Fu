package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class MarkLesson(
    @Json(name = "value") val value: MarkLessonValue,
    @Json(name = "description") val description: String,
    @Json(name = "lessonStartDateTime") val lessonStartDateTime: LocalDate?
)