package ru.tstst.schoolboy.domain.journal

import java.time.LocalDateTime

data class AchievementInfo(
    val achievement: Achievement,
    val lastAchievementDate: LocalDateTime
) {
    var counter: Int = 1
        private set

    fun incrementCounter() {
        ++counter
    }
}