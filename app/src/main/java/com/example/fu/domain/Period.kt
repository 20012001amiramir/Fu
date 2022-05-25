package ru.tstst.schoolboy.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class Period(
    @Json(name = "begin") val begin: LocalDateTime,
    @Json(name = "end") val end: LocalDateTime
): Serializable {

    fun contains(date: LocalDate): Boolean {
        val beginDate = begin.toLocalDate()
        val endDate = end.toLocalDate()
        return date.isEqual(beginDate)
                || (date.isAfter(beginDate) && date.isBefore(endDate))
                || date.isEqual(endDate)
    }

    companion object {
        fun oneDay(date: LocalDate): Period {
            return Period(
                date.atTime(0, 0),
                date.atTime(23, 59)
            )
        }

        fun oneWeek(date: LocalDate): Period {
            val today = date.atStartOfDay()
            return Period(
                today,
                today.plusWeeks(1).minusSeconds(1)
            )
        }
    }
}