package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import com.example.fu.data.network.Api
import ru.tstst.schoolboy.domain.Period
import ru.tstst.schoolboy.domain.schedule.Schedule
import com.example.fu.util.valueAsUnique
import java.time.LocalDate
import javax.inject.Inject


class ScheduleRepository @Inject constructor(
    private val api: Api
) {
    private val scheduleFlow = MutableStateFlow<Schedule?>(null)

    fun getScheduleFlow(): Flow<Schedule> = scheduleFlow.filterNotNull()

    suspend fun loadSchedule(date: LocalDate) {
        val periodRequest = PeriodRequest(Period.oneDay(date))
        scheduleFlow.valueAsUnique = api.getSchedule(periodRequest)
    }
}