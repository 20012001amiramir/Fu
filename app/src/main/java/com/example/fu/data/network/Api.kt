package com.example.fu.data.network

import retrofit2.http.*
import ru.tstst.schoolboy.data.network.response.*
import ru.tstst.schoolboy.domain.notifications.NotificationsViewed

interface Api {


    @POST("notifications/mark-viewed")
    suspend fun markNotificationViewed(@Body request: NotificationsViewed) : NotificationsViewedResponse
    //end region


}