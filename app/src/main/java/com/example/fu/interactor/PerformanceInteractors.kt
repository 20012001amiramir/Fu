package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.tstst.schoolboy.data.network.response.GetAverageMark
import ru.tstst.schoolboy.data.network.response.GetClassroomMarksResponse
import ru.tstst.schoolboy.data.network.response.GetSubjectAverageMarkListResponse
import ru.tstst.schoolboy.data.repository.RankPersonsRepository
import ru.tstst.schoolboy.domain.performance.SubjectFinalMarkWithClass
import ru.tstst.schoolboy.domain.performance.SubjectYearMark
import javax.inject.Inject

class PerformanceInteractors @Inject constructor(
    private val markRepository: MarkRepository,
    private val rankRepository: RankPersonsRepository
) {

    fun getSubjectAverageMarkListFlow(academicTermId: String): Flow<GetSubjectAverageMarkListResponse> =
        markRepository
            .getSubjectAverageMarkList(academicTermId)

    suspend fun refreshSubjectAverageMarkList(academicTermId: String) =
        markRepository.refreshSubjectAverageMarkList(academicTermId)

    fun getAverageMarkFlow(academicTermId: String, subject: String): Flow<GetAverageMark> =
        markRepository
            .getAverageFlow(academicTermId, subject)

    suspend fun refreshAverageMark(academicTermId: String, subject : String) =
        markRepository.refreshAverage(academicTermId, subject)

    suspend fun refreshClassroomMarksRating(academicTermId: String) =
        rankRepository.refreshRank(academicTermId)

    fun getClassroomMarksRating(academicTermId: String): Flow<GetClassroomMarksResponse> =
        rankRepository.getRankFlow(academicTermId)

    fun getSubjectYearMarkListFlow(academicTermId: String): Flow<List<SubjectYearMark>> =
        markRepository
            .getSubjectYearMarkListFlow(academicTermId)
            .map { list ->
                list.sortedWith(
                    compareBy(
                        String.CASE_INSENSITIVE_ORDER
                    ) { it.subject.title }
                )
            }

    suspend fun refreshSubjectYearMarkList(academicTermId: String) =
        markRepository.refreshSubjectYearMarkList(academicTermId)

    fun getSubjectFinalMarkWithClassFlow(academicTermId: String): Flow<SubjectFinalMarkWithClass> =
        markRepository
            .getSubjectFinalMarkWithClassFlow(academicTermId)
            .map {
                it.copy(
                    subjectFinalMarks = it.subjectFinalMarks.sortedWith(
                        compareBy(
                            String.CASE_INSENSITIVE_ORDER
                        ) { subjectFinalMark -> subjectFinalMark.subject.title }
                    )
                )
            }

    suspend fun refreshSubjectFinalMarkWithClass(academicTermId: String) =
        markRepository.refreshSubjectFinalMarkWithClass(academicTermId)

    fun getSubjectPerformanceFlow(academicTermId: String, subjectId: String) =
        markRepository.getSubjectPerformanceFlow(academicTermId, subjectId)

    suspend fun refreshSubjectPerformance(academicTermId: String, subjectId: String) {
        markRepository.refreshSubjectPerformance(academicTermId, subjectId)
    }
}