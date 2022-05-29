package com.example.fu.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fu.data.network.request.AddGarbageRequest
import com.example.fu.data.network.response.AddGarbageResponse
import com.example.fu.data.network.response.GarbagesResponse
import com.example.fu.data.repository.GarbageRepository
import com.example.fu.util.ErrorHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
class AddGarbageDialogViewModel @Inject constructor(
    private val garbageRepository: GarbageRepository,
    private val errorHandler: ErrorHandler
): ViewModel() {

    private val _addGarbageState: MutableLiveData<AddGarbageViewState> = MutableLiveData(
        AddGarbageViewState.BlankViewState)
    val addGarbageState: LiveData<AddGarbageViewState> get() = _addGarbageState

    init {
        viewModelScope.launch {
            val gatGarbageFlow =
                garbageRepository
                    .getAddGarbageFlow()

            gatGarbageFlow.collect{
                _addGarbageState.value = AddGarbageViewState.Data(it)
            }
        }
    }

    fun addGarbage(garbage: AddGarbageRequest) {
        _addGarbageState.value = AddGarbageViewState.LoadingViewState
        viewModelScope.launch {
            try {
                garbageRepository.addGarbage(garbage)
            } catch (error: Throwable) {
                _addGarbageState.value = AddGarbageViewState.LoadingError(error)
                errorHandler.proceed(error)
            }
        }
    }

}
sealed class AddGarbageViewState {
    data class Data(val data: AddGarbageResponse) : AddGarbageViewState()
    object LoadingViewState : AddGarbageViewState()
    object BlankViewState : AddGarbageViewState()
    data class LoadingError(val error: Throwable) : AddGarbageViewState()
}