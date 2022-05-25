package ru.tstst.schoolboy.domain.story

import android.graphics.Color
import com.squareup.moshi.Json
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class MetaEntity(
    @Json(name = "created_at") val created_at: LocalDateTime,
    @Json(name = "published_at") val published_at: LocalDateTime,
    @Json(name = "updated_at") val updated_at: LocalDateTime,
    @Json(name = "deadline") val deadline: LocalDateTime
) : Serializable
