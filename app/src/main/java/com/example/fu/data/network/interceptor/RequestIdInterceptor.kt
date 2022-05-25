package com.example.fu.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class RequestIdInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain
            .request()
            .newBuilder()
            .header("X-Request-ID", "${UUID.randomUUID()}")
            .build()
            .run {
                chain.proceed(this)
            }
}