package ru.tstst.schoolboy.domain.journal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DigestCardMotivation(
    @Json(name = "id") id: String,
    @Json(name = "image") image: String,
    @Json(name = "previewImage") previewImage: String,
    @Json(name = "title") title: String,
    @Json(name = "text") val description: String,
    @Json(name = "motivation") val motivation: String,
) : DigestCard(DigestCardType.MOTIVATION, id, image, previewImage, title)