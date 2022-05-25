package ru.tstst.schoolboy.domain.notifications

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationsViewed(
    @Json(name = "id") val id: String
)
