package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.tstst.schoolboy.data.persistent.LocalStorage
import ru.tstst.schoolboy.domain.Language
import ru.tstst.schoolboy.domain.createLanguage
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SettingsRepository @Inject constructor(
    private val localStorage: LocalStorage
) {

    private val _languageChangeFlow: MutableStateFlow<Language?> = MutableStateFlow(null)

    val languageChangeFlow: Flow<Language?>
        get() = _languageChangeFlow

    fun currentLanguage(): Language = createLanguage(localStorage.languageTag)

    fun updateLanguage(language: Language) {
        if (language.tag != localStorage.languageTag) {
            localStorage.languageTag = language.tag

            _languageChangeFlow.value = language

            //StateFlow restriction - it always emits it's latest state
            //So we need to reset it to null
            _languageChangeFlow.value = null
        }
    }

}