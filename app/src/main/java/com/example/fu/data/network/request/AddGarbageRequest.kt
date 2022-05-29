package com.example.fu.data.network.request

import com.example.fu.data.network.response.DataToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddGarbageRequest(
    @Json(name = "name")
    val name: String?,
    @Json(name = "garbageTypes")
    val garbageTypes: Int?,
    @Json(name = "barcode")
    val barcode: String?,
    @Json(name = "image")
    val image: String?
)