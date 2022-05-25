package ru.tstst.schoolboy.domain.lesson

import com.squareup.moshi.Json

enum class AbsentReason {
    @Json(name = "ABSENT") SKIPPED,
    @Json(name = "ILL") SICK
}