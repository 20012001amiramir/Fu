package com.example.fu.interactor

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fu.data.repository.OAuthRepository
import com.example.fu.data.repository.SessionRepository
import com.example.fu.data.persistent.LocalStorage
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
class SessionInteractors @Inject constructor(
    private val oAuthRepository: OAuthRepository,
    private val sessionRepository: SessionRepository,
) {

    fun getAuthErrorFlow() = sessionRepository.authErrorFlow()

    fun proceedAuthError(authError: Throwable) {
        sessionRepository.proceedAuthError(authError)
    }

    suspend fun authorize(email: String, password: String) {
        oAuthRepository.login(email, password)
    }

    suspend fun register(email: String, password: String, returnPassword : String) {
        oAuthRepository.register(email, password, returnPassword)
    }

    fun isAuthorized() = oAuthRepository.isAuthorized()

}