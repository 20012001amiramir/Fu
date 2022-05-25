package ru.tstst.schoolboy.domain.journal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.Period
import java.time.ZoneOffset

@JsonClass(generateAdapter = true)
data class Digest(
    @Json(name = "id") val id: String,
    @Json(name = "period") val period: Period,
    @Json(name = "viewed") val viewed: Boolean,
    @Json(name = "cards") val cards: List<DigestCard>?,
    @Json(name = "achievements") val achievements: List<Achievement>?,
)