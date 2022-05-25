package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.tstst.schoolboy.data.network.request.SettingsRule
import ru.tstst.schoolboy.data.repository.SettingSendRepository
import ru.tstst.schoolboy.domain.Language
import ru.tstst.schoolboy.data.repository.SettingsRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SettingSendAverageInteractor @Inject constructor(
    private val settingsRepository: SettingSendRepository
) {

    suspend fun setCurrentAverage(five: Double, four: Double, three: Double){
        settingsRepository.setCurrentAverage(five, four, three)
    }

    suspend fun getCurrentAverage(): SettingsRule {
        return settingsRepository.getCurrentAverage()
    }

}