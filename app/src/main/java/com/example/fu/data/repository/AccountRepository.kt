package com.example.fu.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import ru.tstst.schoolboy.data.persistent.LocalStorage
import ru.tstst.schoolboy.domain.profile.AccountInfo
import ru.tstst.schoolboy.domain.profile.ActionDialog
import com.example.fu.util.fromJson
import com.example.fu.util.toJson
import com.example.fu.util.valueAsUnique
import ru.tstst.schoolboy.data.repository.OAuthRepository
import ru.tstst.schoolboy.data.repository.ProfileRepository
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
class AccountRepository @Inject constructor(
    private val oAuthRepository: OAuthRepository,
    private val profileRepository: ProfileRepository,
    private val localStorage: LocalStorage
) {

    private val actionFlow = MutableStateFlow<List<ActionDialog>?>(null)
    val accountFlow = localStorage.accountsFlow

    fun changeToken(accessToken: String?, refreshToken: String?) : Boolean {
        return if (localStorage.accountActiveCount ?: -1 > 0) {
            localStorage.possibleRefreshToken = refreshToken
            oAuthRepository.changeToken(accessToken, refreshToken)
            true
        } else {
            clearCache()
            false
        }
    }

    fun getActionFlow() = actionFlow

    fun loadAction(currentProfile: Boolean) {
        actionFlow.valueAsUnique = if (currentProfile) listOf(ActionDialog.Delete) else
            listOf(ActionDialog.Enter, ActionDialog.Delete)
    }


    suspend fun deleteToken(id: String) {
        return withContext(NonCancellable) {
            val accounts = localStorage.dataAccounts
                ?.fromJson<MutableList<AccountInfo>>()
            val deleteAccount = accounts?.find {
                it.id == id
            }
            accounts?.remove(deleteAccount)
            if (deleteAccount?.currentProfile == true) {
                accounts.first().refreshToken?.let { changeToken(accounts.first().accessToken, it) }
                accounts.first().currentProfile = true
            }
            localStorage.dataAccounts = accounts?.toJson()
        }
    }

    suspend fun deleteCurrentAccount() {
        return withContext(NonCancellable) {
            val accounts = localStorage.dataAccounts
                ?.fromJson<MutableList<AccountInfo>>()
            accounts?.let { accounts ->
                accounts.removeIf { it.currentProfile }
                if (accounts.isNotEmpty()) {
                        changeToken(
                            accounts.first().accessToken,
                            accounts.first().refreshToken
                        )
                    accounts.first().currentProfile = true
                } else {
                    clearCache()
                }
            }
            localStorage.dataAccounts = accounts?.toJson()
        }
    }

    private fun clearCache() {
        profileRepository.clearCache()
    }


}