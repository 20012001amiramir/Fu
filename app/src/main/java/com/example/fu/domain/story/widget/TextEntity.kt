package ru.tstst.schoolboy.domain.story.widget

import com.squareup.moshi.Json
import ru.tstst.schoolboy.ui.common.list.RecyclerItem
import java.io.Serializable

open class TextEntity(
    @Json(name = "type") open val type: TypeTextWidgetEnum,
    @Json(name = "text") open val text: String
) : RecyclerItem, Serializable
enum class TypeTextWidgetEnum {
    TITLE, SUBTITLE
}
