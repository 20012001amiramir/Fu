package com.example.fu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class AppActivityViewModel @Inject constructor(

): ViewModel() {


    private val _appViewState = MutableLiveData<AppViewState>()
    val appViewState get() = _appViewState

    init {
        viewModelScope.launch {
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        _appViewState.value = AppViewState.QrReader
    }


    fun notifyScheduleShown() {
        _appViewState.value = AppViewState.CurrentFragmentState
    }

    fun setQrReader(){
        _appViewState.value = AppViewState.QrReader
    }

    sealed class AppViewState {
        object Onboarding : AppViewState()
        object Login : AppViewState()
        object QrReader : AppViewState()
        object Schedule : AppViewState()
        object CurrentFragmentState : AppViewState()
        object BlockingLoading : AppViewState()
        object ForceUpdate : AppViewState()
    }
}