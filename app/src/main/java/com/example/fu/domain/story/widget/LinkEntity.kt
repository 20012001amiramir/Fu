package ru.tstst.schoolboy.domain.story.widget

import com.squareup.moshi.Json
import ru.tstst.schoolboy.ui.common.list.RecyclerItem
import java.io.Serializable

data class LinkEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "serviceType") val serviceType: String,
    @Json(name = "title") val title: String,
    @Json(name = "link") val link: String
) : RecyclerItem, Serializable
