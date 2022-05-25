package ru.tstst.schoolboy.interactor

import ru.tstst.schoolboy.domain.notifications.Page
import javax.inject.Inject

class NotificationsInteractor @Inject constructor(
    private val notificationRepository: NotificationRepository
) {

    fun getNotificationFlow() = notificationRepository.getNotificationFlow()

    suspend fun loadNotifications(page: Page){
        notificationRepository.loadNotifications(page)
    }

    fun getNotificationChangesFlow() = notificationRepository.getNotificationsChangesFlow()

    suspend fun changeNotificationsViewed(id: String){
        notificationRepository.changeNotificationsViewed(id)
    }

}