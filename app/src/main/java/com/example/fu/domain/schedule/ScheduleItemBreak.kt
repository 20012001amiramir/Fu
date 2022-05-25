package ru.tstst.schoolboy.domain.schedule

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period
import java.time.Duration

@JsonClass(generateAdapter = true)
class ScheduleItemBreak(
    @Json(name = "text") text: String,
    @Json(name = "datetime") period: Period
) : ScheduleItem(ScheduleItemType.BREAK, text, period) {

    val hours
        get() = Duration.between(period.begin, period.end).toHours()

    val minutes
        get() = Duration.between(period.begin, period.end)
            .let { it.minusHours(it.toHours()).toMinutes() }
}