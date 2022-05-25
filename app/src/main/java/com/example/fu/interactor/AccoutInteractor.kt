package ru.tstst.schoolboy.interactor

import com.example.fu.data.repository.AccountRepository
import javax.inject.Inject

class AccountInteractor @Inject constructor(
    private val repository: AccountRepository
){

    fun changeAccount(access: String?, refreshToken: String?) : Boolean {
        return repository.changeToken(access, refreshToken)
    }

    suspend fun deleteAccount(id: String) {
        repository.deleteToken(id)
    }
    fun loadAction(currentProfile: Boolean) = repository.loadAction(currentProfile)

    fun getAction() = repository.getActionFlow()

    fun getAccountsFlow() = repository.accountFlow

    suspend fun deleteCurrentAccount() {
        repository.deleteCurrentAccount()
    }

}