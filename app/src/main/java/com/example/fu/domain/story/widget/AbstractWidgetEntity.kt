package ru.tstst.schoolboy.domain.story.widget

import com.squareup.moshi.Json
import ru.tstst.schoolboy.domain.profile.ClassType
import java.io.Serializable

data class AbstractWidgetEntity(
    @Json(name = "type") var type: TypeAbstractWidgetEnum,
    @Json(name = "payload") var payload: TypeStoryElement
) : Serializable
enum class TypeAbstractWidgetEnum {
    TEXT, POLL, LINK, POLLOFFED
}

