package ru.tstst.schoolboy.domain.profile

import com.squareup.moshi.Json

enum class ClassType {
    @Json(name = "ZERO")
    ZERO,
    @Json(name = "FIRST")
    FIRST,
    @Json(name = "SECOND")
    SECOND,
    @Json(name = "THIRD")
    THIRD,
    @Json(name = "FOURTH")
    FOURTH,
    @Json(name = "FIFTH")
    FIFTH,
    @Json(name = "SIXTH")
    SIXTH,
    @Json(name = "SEVENTH")
    SEVENTH,
    @Json(name = "EIGHTH")
    EIGHTH,
    @Json(name = "NINTH")
    NINTH,
    @Json(name = "TENTH")
    TENTH,
    @Json(name = "ELEVENTH")
    ELEVENTH,
    @Json(name = "TWELFTH")
    TWELFTH,
}