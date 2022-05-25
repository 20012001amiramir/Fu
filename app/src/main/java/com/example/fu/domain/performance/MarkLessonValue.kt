package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json

enum class MarkLessonValue {
    @Json(name = "AWFUL")
    AWFUL,
    @Json(name = "BAD")
    BAD,
    @Json(name = "AVERAGE")
    AVERAGE,
    @Json(name = "GOOD")
    GOOD,
    @Json(name = "EXCELLENT")
    EXCELLENT,
    @Json(name = "DASH")
    DASH
}

//TODO: (after backend mock is ready) remove this
fun MarkLessonValue.getMarkNumber(): Int =
    when(this) {
        MarkLessonValue.AWFUL -> 1
        MarkLessonValue.BAD -> 2
        MarkLessonValue.AVERAGE -> 3
        MarkLessonValue.GOOD -> 4
        MarkLessonValue.EXCELLENT -> 5
        MarkLessonValue.DASH -> 0
    }