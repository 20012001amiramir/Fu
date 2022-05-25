package ru.tstst.schoolboy.domain.schedule

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.lesson.Classroom
import ru.tstst.schoolboy.domain.Period
import ru.tstst.schoolboy.domain.performance.MarkLesson

@JsonClass(generateAdapter = true)
class ScheduleItemLesson(
    @Json(name = "text") text: String,
    @Json(name = "datetime") period: Period,
    @Json(name = "classroom") val classroom: Classroom?,
    @Json(name = "lessonId") val lessonId: String,
    @Json(name = "marks") val markList: List<MarkLesson>?,
    @Json(name = "skip") val skip: Array<String>? = null
) : ScheduleItem(ScheduleItemType.LESSON, text, period)