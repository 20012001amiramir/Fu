package ru.tstst.schoolboy.domain.journal

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import com.example.fu.data.network.model.SilentError
import javax.inject.Inject

class HasUnreadDigestsUC @Inject constructor(
    private val journalRepository: JournalRepository,
    private val errorHandler: ru.tstst.schoolboy.util.ErrorHandler
) {
    fun execute(): Flow<Boolean> {
        return journalRepository.getDigestsFlow()
            .map { digests ->
                digests.any { !it.viewed }
            }
            .onStart {
                try {
                    journalRepository.refreshDigests()
                } catch (e: Throwable) {
                    // It seems ok for this UC to fail silently
                    // and just don't indicate unread digests for a while.
                    errorHandler.proceed(SilentError(e))
                }
                emit(false) // The default state
            }
    }
}