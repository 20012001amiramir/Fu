package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import com.example.fu.data.network.Api
import com.example.fu.util.valueAsUnique
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val api: Api
) {
    private val authErrorFlow = MutableStateFlow<Throwable?>(null)

    var minSupportedVersionCode: Int? = null
    var mwVersion: String? = null

    fun authErrorFlow(): Flow<Throwable> = authErrorFlow.filterNotNull()

    fun proceedAuthError(authError: Throwable) {
        authErrorFlow.valueAsUnique = authError
    }

    suspend fun getVersions() {
        return api.getVersions()
            .let {
                minSupportedVersionCode = it.android.applicationCode
                mwVersion = it.mw.mwVersion // Sad, but yes, we get the mw version in this request.
            }
    }

    suspend fun confirmUserIsActive() {
        return api.confirmUserIsActive()
    }
}