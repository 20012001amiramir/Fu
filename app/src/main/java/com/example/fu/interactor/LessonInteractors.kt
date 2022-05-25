package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.flow.Flow
import ru.tstst.schoolboy.domain.lesson.Lesson
import javax.inject.Inject

class LessonInteractors @Inject constructor(
    private val lessonRepository: LessonRepository
) {

    fun getLessonFlow(lessonId: String): Flow<Lesson> {
        return lessonRepository.getLessonFlow(lessonId)
    }

    suspend fun refreshLesson(lessonId: String) {
        lessonRepository.refreshLesson(lessonId)
    }
}