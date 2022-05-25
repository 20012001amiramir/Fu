package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json

enum class MarkProgress {
    @Json(name = "UP")
    UP,
    @Json(name = "DOWN")
    DOWN,
    @Json(name = "STABLE")
    STABLE
}