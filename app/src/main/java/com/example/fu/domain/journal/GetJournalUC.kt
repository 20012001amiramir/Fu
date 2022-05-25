package ru.tstst.schoolboy.domain.journal

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetJournalUC @Inject constructor(
    private val repository: JournalRepository
) {
    fun execute(): Flow<Journal> = repository.getDigestsFlow().map { Journal(it) }
}