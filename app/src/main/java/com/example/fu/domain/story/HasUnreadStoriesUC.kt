package ru.tstst.schoolboy.domain.story

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import com.example.fu.data.network.model.SilentError
import ru.tstst.schoolboy.data.repository.ProfileRepository
import javax.inject.Inject

class HasUnreadStoriesUC @Inject constructor(
    private val repository: ProfileRepository,
    private val errorHandler: ru.tstst.schoolboy.util.ErrorHandler
) {
    fun execute(): Flow<Boolean> {
        return repository.getStoriesProfileFlow()
            .filterNot {
                it.second.isNullOrEmpty()
            }
            .map { stories ->
                stories.second!!.any { it.state == StateEnum.NEW  }
            }
            .onStart {
                try {
                    repository.refreshStories()
                } catch (e: Throwable) {
                    // It seems ok for this UC to fail silently
                    // and just don't indicate unread digests for a while.
                    errorHandler.proceed(SilentError(e))
                }
                emit(false) // The default state
            }
    }
}