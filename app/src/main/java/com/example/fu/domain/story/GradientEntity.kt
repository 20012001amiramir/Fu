package ru.tstst.schoolboy.domain.story

import android.graphics.Color
import com.squareup.moshi.Json

data class GradientEntity(
    @Json(name = "title") val title: String,
    @Json(name = "colors") val colors: List<Color>
)
