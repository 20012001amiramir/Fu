package ru.tstst.schoolboy.domain.story.widget

import java.io.Serializable

sealed class TypeStoryElement(): Serializable {
    data class StoryElementText(val itemText : TextEntity) : TypeStoryElement(), Serializable
    data class StoryElementPoll(val itemPoll : PollEntity) : TypeStoryElement(), Serializable
    data class StoryElementLink(val itemLink : LinkEntity) : TypeStoryElement(), Serializable
    data class StoryPollOffed(val itemPoll : PollEntity) : TypeStoryElement(), Serializable
}