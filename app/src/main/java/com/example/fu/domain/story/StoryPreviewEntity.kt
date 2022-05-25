package ru.tstst.schoolboy.domain.story

import com.squareup.moshi.Json
import ru.tstst.schoolboy.domain.story.widget.AbstractWidgetEntity
import java.io.Serializable

data class StoryPreviewEntity (
    @Json(name = "background") val background: BackgroudEntity,
    @Json(name = "title") val title: String
) : Serializable