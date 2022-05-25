package ru.tstst.schoolboy.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import com.example.fu.data.network.Api
import ru.tstst.schoolboy.data.network.model.Feed
import ru.tstst.schoolboy.di.mappers.mapper
import ru.tstst.schoolboy.domain.story.StoryArchiveEntity
import ru.tstst.schoolboy.domain.story.StoryDetailEntity
import com.example.fu.util.valueAsUnique
import javax.inject.Inject

class StoryArchiveRepository @Inject constructor(
    private val api: Api,
) {

    private val storyArchiveFlow = MutableStateFlow<List<StoryArchiveEntity>?>(null)

    private val storyOneItem : MutableLiveData<List<StoryArchiveEntity>> = MutableLiveData()

    fun getStoryArchiveFlow(): Flow<List<StoryArchiveEntity>> = storyArchiveFlow.filterNotNull()

    fun getStoryOneItem(): MutableLiveData<List<StoryArchiveEntity>> = storyOneItem

    suspend fun loadOneItem(categoryX: Int?, limit: Int, offset: Int) {
        storyOneItem.postValue(mapperY(api.getArchiveResponse(StoryArchiveRequest(categoryX, limit, offset)).feed))
    }

    suspend fun loadArchiveStory(categoryX: Int?, limit: Int, offset: Int) {
        storyArchiveFlow.valueAsUnique =
            mapperY(api.getArchiveResponse(StoryArchiveRequest(categoryX, limit, offset)).feed)
    }

    fun mapperY(item: List<GetArchiveResponse>): List<StoryArchiveEntity>{
        return item.map {
            StoryArchiveEntity(
                MutableLiveData(mapperX(it.feed)),
                it.category,
                it.storiesCount
            )
        }
    }

    fun mapperX(item: List<Feed>): MutableList<StoryDetailEntity> {
        return item.map{
            mapper(it)
        }.toMutableList()
    }

}