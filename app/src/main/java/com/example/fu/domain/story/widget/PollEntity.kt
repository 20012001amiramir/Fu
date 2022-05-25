package ru.tstst.schoolboy.domain.story.widget

import com.squareup.moshi.Json
import ru.tstst.schoolboy.ui.common.list.RecyclerItem
import java.io.Serializable

data class PollEntity(
    @Json(name = "serviceType") val serviceType: String,
    @Json(name = "options") val options: List<PollOptionEntity>
) : RecyclerItem, Serializable
