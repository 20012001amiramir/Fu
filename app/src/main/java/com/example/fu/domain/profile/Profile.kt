package ru.tstst.schoolboy.domain.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Profile(
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "lastName") val lastName: String?,
    @Json(name = "middleName") val middleName: String?,
    @Json(name = "birthDay") val birthDate: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "profile_photo") val profilePhoto: String?,
    @Json(name = "photo") val photo: String?,
    @Json(name = "studyClass") val studyClass: StudyClass?,
    @Json(name = "school") val school: School?,
    @Json(name = "interests") val interests: List<String>?,
    @Json(name = "favouriteSubjects") val favouriteSubjects: List<String>?,
    @Json(name = "userId") val userId: String
)