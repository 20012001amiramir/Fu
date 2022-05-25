package ru.tstst.schoolboy.domain.lesson

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Teacher(
    @Json(name = "id") val id: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "middleName") val middleName: String?,
    @Json(name = "profileImage") val profileImage: String?
) {
    private fun getMiddleNameOrSpace(): String = middleName?.let { " $it " } ?: " "
    val fullName: String get() = "$firstName${getMiddleNameOrSpace()}$lastName"
}