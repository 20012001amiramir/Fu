package ru.tstst.schoolboy.domain.notifications

import java.time.LocalDateTime

data class NotificationInfoEntity(
    val value: Int? = null,
    val subject: String? = null,
    var setMarkDate: String = "",
    val type: String? = null,
    val setMarkDateTime: LocalDateTime?

)
