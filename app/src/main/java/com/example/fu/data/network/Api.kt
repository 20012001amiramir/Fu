package com.example.fu.data.network

import com.example.fu.data.network.request.AddGarbageRequest
import com.example.fu.data.network.request.GetGarbageRequest
import com.example.fu.data.network.response.AddGarbageResponse
import retrofit2.http.*

interface Api {


    @POST("Garbage/AddGarbageInfo")
    suspend fun AddGarbagInfo(@Body request: AddGarbageRequest): AddGarbageResponse

    @GET("Garbage")
    suspend fun GetGarbageInfo(@Query("barcode") barcode: String?): AddGarbageResponse

    @GET("Garbage/GetGarbageTypes")
    suspend fun GetGarbageTypes(): AddGarbageResponse

    @GET("Garbage/GetGarbageInfoByAuthorizedUser")
    suspend fun GetGarbageInfoByAuthorizedUser( @Query("barcode") barcode: String?): AddGarbageResponse

    @GET("Garbage/GetGarbagesScanedByAuthorizedUser")
    suspend fun GetGarbagesScanedByAuthorizedUser(): AddGarbageResponse

    @POST("Garbage/AddGarbageFromApiToUserHistory")
    suspend fun AddGarbageFromApiToUserHistory(@Body request: GetGarbageRequest): AddGarbageResponse

    @GET("Garbage/GetGarbageFromApiUserHistory")
    suspend fun GetGarbageFromApiUserHistory(): AddGarbageResponse

}