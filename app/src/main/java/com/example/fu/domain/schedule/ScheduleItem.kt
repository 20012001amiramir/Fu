package ru.tstst.schoolboy.domain.schedule

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period

@JsonClass(generateAdapter = true)
open class ScheduleItem(
    @Json(name = "type") val type: ScheduleItemType,
    @Json(name = "text") val text: String,
    @Json(name = "datetime") val period: Period
)