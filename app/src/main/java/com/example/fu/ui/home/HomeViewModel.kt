package com.example.fu.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fu.data.network.response.AddGarbageResponse
import com.example.fu.data.repository.GarbageRepository
import com.example.fu.util.ErrorHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
class HomeViewModel @Inject constructor(
    private val garbageRepository: GarbageRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _subjectGarbageState = MutableLiveData<SubjectGarbageGetState>(SubjectGarbageGetState.Blank)
    val subjectGarbageState: LiveData<SubjectGarbageGetState> get() = _subjectGarbageState

    init {
            viewModelScope.launch {
                val gatGarbageFlow =
                    garbageRepository
                        .getGarbageFlow()

                gatGarbageFlow.collect{
                    _subjectGarbageState.value = SubjectGarbageGetState.Data(it)
                }
        }
    }

    fun loadGarbageInfo(barcode: String) {
        _subjectGarbageState.value = SubjectGarbageGetState.LoadingProgress
        viewModelScope.launch {
            try {
                garbageRepository.loadGarbageTypes (barcode)
            } catch (error: Throwable) {
                _subjectGarbageState.value = SubjectGarbageGetState.LoadingError(error)
                errorHandler.proceed(error)
            }
        }
    }

    sealed class SubjectGarbageGetState {

        object Blank : SubjectGarbageGetState()

        object LoadingProgress : SubjectGarbageGetState()

        data class Data(
            val info: AddGarbageResponse
        ) : SubjectGarbageGetState()

        data class LoadingError(val error: Throwable) : SubjectGarbageGetState()
    }

}