package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.tstst.schoolboy.data.network.request.SettingsRule
import ru.tstst.schoolboy.domain.Language
import ru.tstst.schoolboy.data.repository.SettingsRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SettingsInteractors @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    fun currentLanguage() = settingsRepository.currentLanguage()

    fun languageChangeFlow() = settingsRepository.languageChangeFlow

    fun updateLanguage(language: Language) {
        settingsRepository.updateLanguage(language)
    }

}