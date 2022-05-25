package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json

enum class Tense {
    @Json(name = "PAST")
    PAST,
    @Json(name = "CURRENT")
    CURRENT,
    @Json(name = "FUTURE")
    FUTURE
}