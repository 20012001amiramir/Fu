package ru.tstst.schoolboy.domain.homework

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period

@JsonClass(generateAdapter = true)
data class HomeworkProgress(
    @Json(name = "total") val total: Int,
    @Json(name = "completed") val completed: Int,
    @Json(name = "hasMore") val hasMore: Boolean,
    @Json(name = "period") val period: Period
)

// TODO: Also add HomeworkStatus to this package. But clarify the format. API is strange.