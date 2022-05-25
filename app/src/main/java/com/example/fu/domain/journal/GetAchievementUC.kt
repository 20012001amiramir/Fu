package ru.tstst.schoolboy.domain.journal

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAchievementUC @Inject constructor(
    private val getJournalUC: GetJournalUC
) {
    fun execute(achievementType: String): Flow<AchievementInfo?> =
        getJournalUC
            .execute()
            .map { journal ->
                journal.achievementInfos.find { it.achievement.type == achievementType }
            }
}