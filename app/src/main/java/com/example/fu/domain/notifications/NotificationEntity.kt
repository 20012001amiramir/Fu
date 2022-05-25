package ru.tstst.schoolboy.domain.notifications

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationEntity(
    @Json(name = "id") val id: String,
    @Json(name = "info") val info: NotificationInfoEntity,
    @Json(name = "type") val type: String,
    @Json(name = "viewed") var viewed: Boolean,
    @Json(name = "update") val update: Long? = null,
    @Json(name = "countNotViewed") var countNotViewed: Int = -1
)