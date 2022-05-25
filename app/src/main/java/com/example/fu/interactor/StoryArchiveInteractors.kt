package ru.tstst.schoolboy.interactor

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import ru.tstst.schoolboy.data.network.model.Category
import ru.tstst.schoolboy.data.repository.StoryArchiveRepository
import ru.tstst.schoolboy.domain.story.StoryArchiveEntity
import ru.tstst.schoolboy.domain.story.StoryDetailEntity
import javax.inject.Inject

class StoryArchiveInteractors @Inject constructor(
    private val storyArchiveRepository: StoryArchiveRepository
) {

    fun getStoryArchiveFlow(): Flow<List<StoryArchiveEntity>> = storyArchiveRepository.getStoryArchiveFlow()

    suspend fun loadArchiveStory(category: Int?, limit: Int, offset: Int){
        storyArchiveRepository.loadArchiveStory(category, limit, offset)
    }

    fun getStoryOneItem(): MutableLiveData<List<StoryArchiveEntity>> = storyArchiveRepository.getStoryOneItem()

    suspend fun loadOneItem(category: Int?, limit: Int, offset: Int){
        storyArchiveRepository.loadOneItem(category, limit, offset)
    }

}