package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import com.example.fu.data.network.Api
import ru.tstst.schoolboy.data.network.response.GetClassroomMarksResponse
import com.example.fu.util.MutableStateFlowLruCache
import com.example.fu.util.valueAsUnique
import javax.inject.Inject

class RankPersonsRepository @Inject constructor(
    private val api: Api
) {
    private val flows = MutableStateFlowLruCache<String, GetClassroomMarksResponse>(maxSize = 21)

    fun clearCache() {
        flows.evictAll()
    }

    fun getRankFlow(id: String): Flow<GetClassroomMarksResponse> {
        return flows.get(id).filterNotNull()
    }

    suspend fun refreshRank(id: String) {
        flows.get(id).valueAsUnique = api.getClassroomMarksRating(GetClassroomMarkRequest(id))
    }
}