package com.example.fu.ui.enter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fu.interactor.SessionInteractors
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val sessionInteractors: SessionInteractors,
) : ViewModel() {



    suspend fun login(email: String, password: String){
        sessionInteractors.authorize(email, password)
    }
}