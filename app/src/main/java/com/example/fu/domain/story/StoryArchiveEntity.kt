package ru.tstst.schoolboy.domain.story

import androidx.lifecycle.MutableLiveData
import ru.tstst.schoolboy.data.network.model.Category
import ru.tstst.schoolboy.ui.common.list.RecyclerItem

data class StoryArchiveEntity(

    var feed: MutableLiveData<MutableList<StoryDetailEntity>>? = null,

    val category: Category? = null,

    val storiesCount: Int

): RecyclerItem