package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.example.fu.data.network.Api
import ru.tstst.schoolboy.data.network.request.SettingsRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SettingSendRepository @Inject constructor(
    private val api: Api
) {

    suspend fun setCurrentAverage(five: Double, four: Double, three: Double){
        api.sendSettings(SettingsRule(five, four, three))
    }

    suspend fun getCurrentAverage(): SettingsRule {
        return api.getSettings()
    }

}