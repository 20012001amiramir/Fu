package ru.tstst.schoolboy.domain.story.widget

import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Json
import ru.tstst.schoolboy.ui.common.list.RecyclerItem
import java.io.Serializable

data class PollOptionEntity(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "selected") val selected: Boolean
): RecyclerItem, Serializable
