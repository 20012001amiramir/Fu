package ru.tstst.schoolboy.domain.journal

import java.time.ZoneOffset

class Journal(
    val digests: List<Digest>
) {
    val achievementInfos: List<AchievementInfo> by lazy {
        val achievementBadgeList: MutableList<AchievementInfo> = mutableListOf()

        digests
            .filter { it.viewed }
            .sortedByDescending { it.period.end.toInstant(ZoneOffset.UTC).epochSecond }
            .forEach { digest ->
                digest
                    .achievements
                    ?.forEach { achievement ->
                        achievementBadgeList
                            .find { achievement.type == it.achievement.type }
                            ?.incrementCounter()
                            ?: achievementBadgeList.add(
                                AchievementInfo(
                                    achievement,
                                    digest.period.end
                                )
                            )
                    }
            }

        achievementBadgeList.toList()
    }
}