package ru.tstst.schoolboy.domain.schedule

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class Schedule(
    @Json(name = "period") val period: Period,
    @Json(name = "scheduleItems") val items: List<ScheduleItem>
) {
    fun isNotEmpty(): Boolean = items.isNotEmpty()

    fun startDate(): LocalDate {
        return period.begin.toLocalDate()
    }
}