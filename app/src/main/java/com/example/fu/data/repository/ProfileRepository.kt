package ru.tstst.schoolboy.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import com.example.fu.data.network.Api
import ru.tstst.schoolboy.data.persistent.LocalStorage
import ru.tstst.schoolboy.di.mappers.mapper
import ru.tstst.schoolboy.domain.profile.*
import ru.tstst.schoolboy.domain.story.*
import ru.tstst.schoolboy.ui.common.extention.fullName
import com.example.fu.util.fromJson
import com.example.fu.util.toJson
import com.example.fu.util.valueAsUnique
import ru.tstst.schoolboy.widget.WidgetDataProvider
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: Api,
    private val localStorage: LocalStorage,
    private val markRepository: MarkRepository,
    private val lessonRepository: LessonRepository,
    private val oAuthRepository: OAuthRepository,
    private val widgetDataProvider: WidgetDataProvider
) {

    private val profileStoryFlow = MutableStateFlow<Pair<Profile, List<StoryDetailEntity>>?>(null)

    private val storyIdFlow = MutableStateFlow<List<StoryDetailEntity>?>(null)

    private val liveDataHasArchive: MutableLiveData<Boolean?> = MutableLiveData(null)

    private var responseStory: GetStoryResponse? = null

    private var refreshJob: Job? = null

    suspend fun refreshStories() {
        if (refreshJob?.isActive == true) return
        // Non-cancelable to prevent cancelling the job
        // in cases the caller scope is cancelled (like VM closed and new one opened).
        // (If cancelling is not the case it could be changed to coroutineContext{})
        withContext(NonCancellable) {
            refreshJob = launch {
                profileStoryFlow.emit(
                    Pair(
                        api.getUserProfile().apply {
                            checkAccount(this)
                        },
                        mapperX(api.getStories())
                    )
                )
            }
        }
    }

    fun mapperX(item: GetStoryResponse): List<StoryDetailEntity> {
        return item.feed.map {
            mapper(it)
        }
    }

    private suspend fun getStoryResponse() {
        responseStory = api.getStories()
    }

    fun getStoriesProfileFlow(): Flow<Pair<Profile, List<StoryDetailEntity>?>> =
        profileStoryFlow.filterNotNull()

    fun getStoriesFlowFromId(): Flow<List<StoryDetailEntity>> = storyIdFlow.filterNotNull()

    suspend fun loadProfileStory() {
        getStoryResponse()
        profileStoryFlow.value =
            responseStory?.feed?.let {
                Pair(api.getUserProfile().apply {
                    checkAccount(this)
                },
                    it.map { story -> mapper(story) })
            }
        liveDataHasArchive.value = responseStory?.has_favorites
    }

    private fun checkAccount(profile: Profile) {
        localStorage.idProfile = profile.userId
        val accountsData = localStorage.dataAccounts?.fromJson<MutableList<AccountInfo>>()
        accountsData?.forEach { it.currentProfile = false }
        val currentAccount = accountsData?.find { it.id == profile.userId }
        currentAccount?.let {
            updateInfo(it, profile, accountsData)
        } ?: kotlin.run {
            saveAccounts(profile, accountsData)
        }
        localStorage.dataAccounts = accountsData?.toJson()
    }

    private fun updateInfo(
        accountInfo: AccountInfo,
        profile: Profile,
        accountsData: MutableList<AccountInfo>?
    ) {
        accountsData?.remove(accountInfo)
        accountInfo.accessToken = oAuthRepository.accessToken
        accountInfo.currentProfile = true
        accountInfo.refreshToken = localStorage.refreshToken
        accountInfo.fullName = profile.fullName
        accountInfo.photoProfile = profile.profilePhoto
        accountInfo.titleClass = profile.studyClass?.title
        accountsData?.add(0, accountInfo)
    }

    private fun saveAccounts(profile: Profile, accountsData: MutableList<AccountInfo>?) {
        accountsData?.let {
            if (it.size < 5)
                it.add(
                    0,
                    AccountInfo(
                        profile.userId,
                        localStorage.refreshToken,
                        oAuthRepository.accessToken,
                        profile.fullName,
                        profile.profilePhoto,
                        profile.studyClass?.title,
                        true
                    )
                )
        }
    }

    suspend fun loadIdStoryFlow(id: Int) {
        storyIdFlow.valueAsUnique = api.getStoriesFromId(id).feed.map {
            mapper(it)
        }
    }

    fun isArchive(): MutableLiveData<Boolean?> = liveDataHasArchive

    fun setOnBoardingFinished(isFinished: Boolean) {
        localStorage.isOnboardingFinished = isFinished
    }

    fun isOnboardingFinished(): Boolean = localStorage.isOnboardingFinished

    fun clearCache() {
        localStorage.clear()
        markRepository.clearCache()
        lessonRepository.clearCache()
        widgetDataProvider.broadcastUpdateWidget()
    }

}