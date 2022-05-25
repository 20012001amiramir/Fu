package com.example.fu.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.tstst.schoolboy.data.repository.OAuthRepository
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().let { request ->
            chain.proceed(
                oAuthRepository.accessToken
                    ?.let { token ->
                        request
                            .newBuilder()
                            .header("Authorization", "accept $token")
                            .build()
                    }
                    ?: request
            )
        }
}