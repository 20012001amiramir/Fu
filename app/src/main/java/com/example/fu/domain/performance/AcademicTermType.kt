package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json

enum class AcademicTermType {
    @Json(name = "QUARTER") QUARTER,
    @Json(name = "TRIMESTER") TRIMESTER,
    @Json(name = "SEMESTER") SEMESTER,
    @Json(name = "YEAR") YEAR,
    @Json(name = "FINAL") FINAL
}