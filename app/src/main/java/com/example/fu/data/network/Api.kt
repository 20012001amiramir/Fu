package com.example.fu.data.network

import com.example.fu.data.network.request.AddGarbageRequest
import com.example.fu.data.network.response.AddGarbageResponse
import retrofit2.http.*

interface Api {


    @POST("Garbage/AddGarbageInfo")
    suspend fun AddGarbagInfo(@Body request: AddGarbageRequest): AddGarbageResponse

    @POST("Garbage/AddGarbageInfo")
    suspend fun GetGarbageInfo(@Body request: AddGarbageRequest): AddGarbageResponse

    @POST("Garbage/AddGarbageInfo")
    suspend fun AddGarbagInfo(@Body request: AddGarbageRequest): AddGarbageResponse

    @POST("Garbage/AddGarbageInfo")
    suspend fun AddGarbagInfo(@Body request: AddGarbageRequest): AddGarbageResponse

    @POST("Garbage/AddGarbageInfo")
    suspend fun AddGarbagInfo(@Body request: AddGarbageRequest): AddGarbageResponse



}