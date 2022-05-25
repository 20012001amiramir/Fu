package ru.tstst.schoolboy.domain.story.widget

import java.io.Serializable


data class TitleTextEntity (
    override val text: String
        ): TextEntity(TypeTextWidgetEnum.TITLE, text), Serializable