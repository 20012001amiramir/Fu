package com.example.fu.data.network.interceptor

import com.example.fu.data.network.model.*
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.HttpException
import ru.tstst.schoolboy.data.repository.OAuthRepository
import ru.tstst.schoolboy.interactor.SessionInteractors
import com.example.fu.util.ErrorHandler
import toothpick.Lazy
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val oAuthRepository: OAuthRepository,
    private val moshi: Moshi,
    private val errorHandler: ErrorHandler,
    private val sessionInteractors: Lazy<SessionInteractors>
) : Authenticator {

    private var tryCount = 0
    private var error : RefreshTokenTooEarly? = null

    override fun authenticate(route: Route?, response: Response): Request? {
        //TODO use kotlin flow with retry
        // TODO: (Discuss) If authorized call made before oauth it will try to refreshToken here and spams with "Unauthorized" snacks from oauthRepository.refreshTokensInternal
        // TODO: (Discuss) Real retry count is 4 now with this logic. And it continues to retry after 0 if not cancelled by scope.
        runBlocking {
            try {
                if (tryCount <= 3) {
                    ++tryCount
                    oAuthRepository.refreshTokens() //TODO make refreshToken get token
                    sessionInteractors.get().confirmUserIsActiveIfNeeded()
                } else {
                    tryCount = 0
                    throw TechnicalError(401, "Auth failed")
                    // TODO: (Discuss) This logic is accidentally broken. The error doesn't switch us to login screen, but shows snack with "Auth failed"
                }
            } catch (error: Throwable) {
                handleRefreshTokenError(error)
            }
        }
        error?.let {
            oAuthRepository.changeToken(it.access_token, it.refresh_token)
        }
        return response
            .request
            .newBuilder()
            .header("Authorization", "Bearer ${oAuthRepository.accessToken}")
            .build()
    }

    private fun handleRefreshTokenError(error: Throwable) {
        if (error is HttpException) {
            error.response()?.errorBody()?.string()?.let { errorString ->
                try {
                    when (error.code()) {
                        425 -> {
                            parseTooEarly(errorString)
                        }
                        else -> parseErrorResponse(errorString)
                    }
                } catch (e: Throwable) {
                    errorHandler.proceed(error)
                }
            }
        } else {
            errorHandler.proceed(error)
        }
    }

    private fun parseTooEarly(errorString: String) {
        moshi
            .adapter(RefreshTokenTooEarlyResponse::class.java)
            .fromJson(errorString)
            ?.let { errorResponse ->
                errorHandler.proceed(
                    RefreshTokenTooEarly(
                        errorResponse.access_token,
                        errorResponse.refresh_token
                    ).apply {
                        error = this
                    }
                )
            }
    }

    private fun parseErrorResponse(errorString: String) {
        moshi
            .adapter(RefreshTokenErrorResponse::class.java)
            .fromJson(errorString)
            ?.let { errorResponse ->
                errorHandler.proceed(
                    RefreshTokenError(
                        errorResponse.errorType,
                        errorResponse.errorDescription,
                    )
                )
            }
    }
}