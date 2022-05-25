package ru.tstst.schoolboy.data.repository

import com.example.fu.data.network.Api
import javax.inject.Inject

class StateSendRepository @Inject constructor(
    private val api: Api
) {
    suspend fun sendButton(group: Int, slide: Int, button : Int) : StatusSendResponse {
        return api.sendButton(StoryButtonRequest(group, slide, button))
    }
    suspend fun sendWatched(group: Int, slide: Int) : StatusSendResponse{
        return api.sendWatched(StoryWatchedRequest(group, slide))
    }
    suspend fun sendPollStates(group: Int, slide: Int, quiz_option : Int) : StatusSendResponse{
        return api.sendPollStates(StoryStatePollRequest(group, slide, quiz_option))
    }
}