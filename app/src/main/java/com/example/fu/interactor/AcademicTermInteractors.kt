package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.flow.Flow
import ru.tstst.schoolboy.domain.performance.AcademicTerms
import javax.inject.Inject

class AcademicTermInteractors @Inject constructor(
    private val academicTermRepository: AcademicTermRepository
) {

    fun getAcademicTermsFlow(): Flow<AcademicTerms> =
        academicTermRepository.getAcademicTermsFlow()

    suspend fun refreshAcademicTerms() =
        academicTermRepository.refreshAcademicTerms()

    suspend fun refreshAcademicTermsIfNotCached() {
        if (!academicTermRepository.hasCachedAcademicTerms()) {
            academicTermRepository.refreshAcademicTerms()
        }
    }
}