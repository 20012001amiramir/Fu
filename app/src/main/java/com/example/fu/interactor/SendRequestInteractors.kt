package ru.tstst.schoolboy.interactor

import ru.tstst.schoolboy.data.repository.StateSendRepository
import javax.inject.Inject

class SendRequestInteractors @Inject constructor(
    private val repository: StateSendRepository
) {
    suspend fun sendButton(group: Int, slide: Int, button : Int) : StatusSendResponse {
        return repository.sendButton(group, slide, button)
    }
    suspend fun sendWatched(group: Int, slide: Int) : StatusSendResponse {
        return repository.sendWatched(group, slide)
    }
    suspend fun sendPollStates(group: Int, slide: Int, quiz_option : Int) : StatusSendResponse {
        return repository.sendPollStates(group, slide, quiz_option)
    }
}