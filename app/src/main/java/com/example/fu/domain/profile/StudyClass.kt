package ru.tstst.schoolboy.domain.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudyClass(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "classType") val classType: ClassType
)