package ru.tstst.schoolboy.domain.story

import com.squareup.moshi.Json
import java.io.Serializable

data class StoryCategoryEntity (
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
) : Serializable