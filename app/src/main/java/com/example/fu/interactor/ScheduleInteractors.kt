package ru.tstst.schoolboy.interactor

//TODO: (after finished entity layer) remove all VO objects from interactor-repository and use them only in Fragment
import kotlinx.coroutines.flow.Flow
import ru.tstst.schoolboy.data.repository.ScheduleRepository
import ru.tstst.schoolboy.domain.schedule.Schedule
import java.time.LocalDate
import javax.inject.Inject

class ScheduleInteractors @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    fun getScheduleFlow(): Flow<Schedule> = scheduleRepository.getScheduleFlow()

    suspend fun loadSchedule(date: LocalDate) {
        scheduleRepository.loadSchedule(date)
    }
}