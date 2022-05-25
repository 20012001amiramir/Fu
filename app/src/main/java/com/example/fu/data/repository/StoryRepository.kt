package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.fu.data.network.Api
import ru.tstst.schoolboy.di.mappers.mapper
import ru.tstst.schoolboy.domain.story.StoryDetailEntity
import ru.tstst.schoolboy.domain.story.StoryEntity
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val api: Api
) {
    private val storiesFlow = MutableSharedFlow<StoryDetailEntity>(1)
    private var refreshStoriesJob: Job? = null

    private fun getStoriesDetailFlow(): Flow<StoryDetailEntity> = storiesFlow.asSharedFlow()

    suspend fun downloadStoriesDetail(id: String) {
        if (refreshStoriesJob?.isActive == true) return
        withContext(NonCancellable) {
            refreshStoriesJob = launch {
                storiesFlow.emit(mapper(api.getStoriesDetailEntity(StoryMasterRequest(id))))
            }
        }
    }

    fun getStoryEntityFlow(): Flow<List<StoryEntity>> {
        return getStoriesDetailFlow().map{
            it.stories
        }
    }

}