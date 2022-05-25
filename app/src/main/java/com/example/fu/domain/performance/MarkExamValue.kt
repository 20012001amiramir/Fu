package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json

enum class MarkExamValue {
    @Json(name = "SUCCESS")
    SUCCESS,
    @Json(name = "FAILURE")
    FAILURE,
    @Json(name = "FREED")
    FREED,
    @Json(name = "NOT_CERTIFIED")
    NOT_CERTIFIED,
    @Json(name = "NOT_CERTIFIED_ILL")
    NOT_CERTIFIED_ILL,
    @Json(name = "NOT_CERTIFIED_MISSED")
    NOT_CERTIFIED_MISSED,
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