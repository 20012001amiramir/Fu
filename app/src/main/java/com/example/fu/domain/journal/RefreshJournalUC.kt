package ru.tstst.schoolboy.domain.journal

import javax.inject.Inject

class RefreshJournalUC @Inject constructor(
    private val repository: JournalRepository
) {
    suspend fun execute() {
        repository.refreshDigests()
    }
}