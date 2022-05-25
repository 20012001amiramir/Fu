package ru.tstst.schoolboy.domain.story

import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Json
import ru.tstst.schoolboy.domain.story.widget.AbstractWidgetEntity
import ru.tstst.schoolboy.ui.common.list.RecyclerItem
import java.io.Serializable

data class StoryEntity (
    @Json(name = "id") val id: String,
    @Json(name = "viewed") val viewed: StateEnum,
    @Json(name = "background") val background: BackgroudEntity,
    @Json(name = "widgets") val widgets: List<AbstractWidgetEntity>
): RecyclerItem, Serializable
enum class StateEnum {
    NEW, WATCHED
}