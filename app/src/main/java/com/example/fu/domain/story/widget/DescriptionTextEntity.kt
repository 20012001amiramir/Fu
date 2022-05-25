package ru.tstst.schoolboy.domain.story.widget

import java.io.Serializable


data class DescriptionTextEntity (
    override val text: String
        ): TextEntity(TypeTextWidgetEnum.SUBTITLE, text), Serializable