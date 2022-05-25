package ru.tstst.schoolboy.data.repository

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import okhttp3.HttpUrl
import com.example.fu.data.network.AuthApi
import com.example.fu.data.network.model.TechnicalError
import ru.tstst.schoolboy.data.persistent.LocalStorage
import ru.tstst.schoolboy.domain.OAuthUrlData.Companion.OAuthUrlData
import com.example.fu.util.ifNotNull
import javax.inject.Inject

class OAuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val localStorage: LocalStorage
) {
    var accessToken: String? = null
        private set

    private val refreshTokenContext = newSingleThreadContext("RefreshTokenContext")
    private var refreshTokensJob: Job? = null

    suspend fun getOAuthToken(code: String) {
        localStorage
            .oAuthUrlData
            ?.let { oAuthData ->
                val tokenResponse = authApi.refreshToken(
                    oAuthData.
                )
                localStorage.refreshToken = tokenResponse.refreshToken
                accessToken = tokenResponse.accessToken
            }
            ?: run {
                throw TechnicalError(401, "Unauthorized")
            }
    }

    suspend fun refreshTokens() {
        // We confine to single-threaded context to fix concurrent access to the refreshTokensJob.
        // Solution is from the docs: https://kotlinlang.org/docs/reference/coroutines/shared-mutable-state-and-concurrency.html#thread-confinement-coarse-grained
        withContext(refreshTokenContext) {
            if (refreshTokensJob?.isActive == true) {
                // Just wait already running refresh
                refreshTokensJob?.join()
            } else {
                refreshTokensJob = launch {
                    refreshTokensInternal()
                }
            }
        }
    }

    private suspend fun refreshTokensInternal() {
        ifNotNull(
            localStorage.refreshToken,
            localStorage.oAuthUrlData
        ) { refreshToken, oAuthUrlData ->
            val tokenResponse = authApi.refreshOAuthToken(
                oAuthUrlData.clientId,
                refreshToken
            )
            localStorage.refreshToken = tokenResponse.refreshToken
            accessToken = tokenResponse.accessToken
            localStorage.possibleRefreshToken = null
        } ?: run {
            throw TechnicalError(401, "Unauthorized")
        }
    }

    suspend fun revokeToken() {
        ifNotNull(
            localStorage.refreshToken,
            localStorage.oAuthUrlData
        ) { refreshToken, oAuthUrlData ->
            authApi.revokeOAuthToken(oAuthUrlData.clientId, refreshToken)
        }
    }

    fun getOAuthUrl(): String {
        val oAuthData = OAuthUrlData()

        localStorage.oAuthUrlData = oAuthData

        val url = HttpUrl.Builder()
            .scheme("https")
            .host(oAuthData.oAuthBaseUrl)
            .addPathSegments(oAuthData.oAuthUrl)
            .addQueryParameter("client_id", oAuthData.clientId)
            .addQueryParameter("response_type", oAuthData.responseType)
            .addQueryParameter("redirect_uri", oAuthData.redirectUri)
            .addQueryParameter("state", oAuthData.state)
            .addQueryParameter("code_challenge", oAuthData.codeChallenge)
            .addQueryParameter("code_challenge_method", oAuthData.codeChallengeMethod)
            .build()
        return url.toString()
    }

    fun isAuthorized() = localStorage.refreshToken != null

    fun changeToken(access: String?, refresh: String?) {
        accessToken = access
        localStorage.refreshToken = refresh
    }
}