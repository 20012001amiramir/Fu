package ru.tstst.schoolboy.domain.story

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import ru.tstst.schoolboy.ui.common.list.RecyclerItem
import java.io.Serializable


@Json(name = "StoryDetailEntity")
data class StoryDetailEntity (

    @SerializedName("id")
    @Json(name = "id")
    val id: String,

    @SerializedName("state")
    @Json(name = "state")
    var state: StateEnum,

    @SerializedName("meta")
    @Json(name = "meta")
    val meta: MetaEntity,

    @SerializedName("category")
    @Json(name = "category")
    val category: StoryCategoryEntity,

    @SerializedName("stories")
    @Json(name = "stories")
    val stories: List<StoryEntity>,

    @SerializedName("preview")
    @Json(name = "preview")
    val preview: StoryPreviewEntity,

    val recyclerId: Int = 0

    ):RecyclerItem, Serializable
