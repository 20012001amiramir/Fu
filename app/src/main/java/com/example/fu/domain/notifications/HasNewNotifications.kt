package ru.tstst.schoolboy.domain.notifications

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import com.example.fu.data.network.model.SilentError
import ru.tstst.schoolboy.util.ErrorHandler
import javax.inject.Inject

class HasNewNotifications @Inject constructor(
    private val notificationsRepository: NotificationRepository,
    private val errorHandler: ErrorHandler
) {

    fun execute(): Flow<Boolean> {
        return notificationsRepository.getNotificationFlow()
            .filterNot {
                it.isNullOrEmpty()
            }
            .map { notification ->
                notification.first().countNotViewed > 0
            }
            .onStart {
                try {
                    notificationsRepository.refreshNotification()
                } catch (e: Throwable) {
                    // It seems ok for this UC to fail silently
                    // and just don't indicate unread digests for a while.
                    errorHandler.proceed(SilentError(e))
                }
                emit(false) // The default state
            }
    }

}