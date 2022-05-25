package ru.tstst.schoolboy.domain.journal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DigestCardInformation(
    @Json(name = "id") id: String,
    @Json(name = "image") image: String,
    @Json(name = "previewImage") previewImage: String,
    @Json(name = "title") title: String,
    @Json(name = "text") val description: String
) : DigestCard(DigestCardType.INFORMATION, id, image, previewImage, title)