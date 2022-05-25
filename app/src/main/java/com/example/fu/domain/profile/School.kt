package ru.tstst.schoolboy.domain.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class School(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String?,
    @Json(name = "head") val headName: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "type") val type: SchoolType,
    @Json(name = "region") val region: String?
)