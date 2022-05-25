package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import ru.tstst.schoolboy.data.network.request.CompletionStatusFilter
import ru.tstst.schoolboy.domain.Period
import ru.tstst.schoolboy.domain.homework.Homework
import ru.tstst.schoolboy.domain.homework.HomeworkProgress
import java.time.LocalDate
import javax.inject.Inject

class HomeworkInteractors @Inject constructor(
    private val homeworkRepository: HomeworkRepository
) {

    fun getHomeworkProgressFlow(date: LocalDate): Flow<HomeworkProgress> {
        return homeworkRepository.getHomeworkProgressFlow(date)
    }

    fun getHomeworkProgressFlowWithoutCachedItem(date: LocalDate): Flow<HomeworkProgress> {
        return homeworkRepository.getHomeworkProgressFlow(date).let {
            if (homeworkRepository.hasCachedHomeworkProgress(date)) it.drop(1) else it
        }
    }

    suspend fun loadHomeworkProgress(date: LocalDate) {
        homeworkRepository.loadHomeworkProgress(date)
    }

    fun getHomeworkActualFlow(): Flow<List<Homework>> = homeworkRepository.getHomeworkActualFlow()

    suspend fun loadActualHomework(
        period: Period,
        completionStatusFilter: CompletionStatusFilter = CompletionStatusFilter.ALL
    ) {
        homeworkRepository.loadActualHomework(period, completionStatusFilter)
    }

    fun getHomeworkArchiveFlow(): Flow<List<Homework>> = homeworkRepository.getHomeworkArchiveFlow()

    suspend fun loadArchiveHomework(
        period: Period,
        completionStatusFilter: CompletionStatusFilter = CompletionStatusFilter.ALL
    ) {
        homeworkRepository.loadArchiveHomework(period, completionStatusFilter)
    }

    fun getHomeworkFlow(): Flow<List<Homework>> = homeworkRepository.getHomeworkFlow()

    suspend fun loadHomework(period: Period) {
        homeworkRepository.loadHomework(period)
    }

    fun getHomeworkProgressChangesFlow(date: LocalDate): Flow<Unit> {
        return homeworkRepository.getHomeworkProgressChangesFlow(date)
    }

    suspend fun changeHomeworkStatus(newHomework: Homework): Boolean {
        return homeworkRepository.changeHomeworkStatus(newHomework)
    }

    suspend fun changeHomeworkStatus(homework: Homework, completed: Boolean, note: String?): Boolean {
        return homeworkRepository.changeHomeworkStatus(homework, completed, note)
    }
}
