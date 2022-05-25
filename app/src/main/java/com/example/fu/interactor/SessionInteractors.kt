package ru.tstst.schoolboy.interactor

import androidx.lifecycle.MutableLiveData
import com.example.fu.data.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import ru.tstst.schoolboy.data.persistent.LocalStorage
import ru.tstst.schoolboy.data.repository.*
import ru.tstst.schoolboy.domain.profile.Profile
import ru.tstst.schoolboy.domain.story.HasArchive
import ru.tstst.schoolboy.domain.story.StoryDetailEntity
import javax.inject.Inject

class SessionInteractors @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val oAuthRepository: OAuthRepository,
    private val sessionRepository: SessionRepository,
    private val academicTermRepository: AcademicTermRepository,
    private val localStorage: LocalStorage,
    private val HasArchive: HasArchive,
    private val tokenRepository: TokenRepository,
    private val accountRepository: AccountRepository
) {

    fun getNightMode() = localStorage.nightMode

    fun getAuthErrorFlow() = sessionRepository.authErrorFlow()

    fun proceedAuthError(authError: Throwable) {
        sessionRepository.proceedAuthError(authError)
    }
    fun getStoryProfileFlow(): Flow<Pair<Profile, List<StoryDetailEntity>?>> = profileRepository.getStoriesProfileFlow()

    fun getStoryFromIdFlow(): Flow<List<StoryDetailEntity>> = profileRepository.getStoriesFlowFromId()

    suspend fun loadProfileStory() = profileRepository.loadProfileStory()

    suspend fun loadStoryFromId(id: Int) = profileRepository.loadIdStoryFlow(id)

    fun getArchive(): MutableLiveData<Boolean?> = HasArchive.getArchive()

    fun getOAuthUrl(): String = oAuthRepository.getOAuthUrl()

    suspend fun authorize(code: String) {
        oAuthRepository.getOAuthToken(code)
        confirmUserIsActive()
    }

    fun setOnboardingFinished(isFinished: Boolean) {
        profileRepository.setOnBoardingFinished(isFinished)
    }

    fun isOnboardingFinished() = profileRepository.isOnboardingFinished()

    fun isAuthorized() = oAuthRepository.isAuthorized()

    suspend fun logout() {
        tokenRepository.unregisterTokenWithUnregister()
        oAuthRepository.revokeToken()
    }

    fun clearCache() {
        profileRepository.clearCache()
        academicTermRepository.clearCache()
    }

    suspend fun isVersionSupported(versionCode: Int): Boolean {
        val minSupportedVersionCode = run {
            if (sessionRepository.minSupportedVersionCode == null) sessionRepository.getVersions()
            requireNotNull(sessionRepository.minSupportedVersionCode)
        }
        return versionCode >= minSupportedVersionCode
    }

    suspend fun confirmUserIsActive() {
        try {
            sessionRepository.confirmUserIsActive()
            localStorage.shouldConfirmUserIsActive = false
        } catch (e: Exception) {
            // confirm later
            localStorage.shouldConfirmUserIsActive = true
        }
    }

    suspend fun confirmUserIsActiveIfNeeded() {
        if (localStorage.shouldConfirmUserIsActive) confirmUserIsActive()
    }
}