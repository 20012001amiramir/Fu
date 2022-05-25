package com.example.fu.data.network

import retrofit2.http.*

interface Api {


    @POST("notifications/mark-viewed")
    suspend fun markNotificationViewed()
    //end region


}