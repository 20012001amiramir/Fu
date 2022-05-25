package ru.tstst.schoolboy.domain.lesson

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period
import ru.tstst.schoolboy.domain.homework.Homework
import ru.tstst.schoolboy.domain.performance.MarkLesson
import ru.tstst.schoolboy.domain.subject.Subject

@JsonClass(generateAdapter = true)
data class Lesson(
    @Json(name = "id") val id: String,
    @Json(name = "period") val period: Period,
    @Json(name = "classroom") val classroom: Classroom?,
    @Json(name = "subject") val subject: Subject,
    @Json(name = "theme") val theme: String?,
    @Json(name = "absenceStatusValues") val absenceReasons: List<AbsentReason>?,
    @Json(name = "comments") val comments: List<String>?,
    @Json(name = "teacher") val teacher: Teacher,
    @Json(name = "homework") val homework: Homework?,
    @Json(name = "marks") val marks: List<MarkLesson>?
)