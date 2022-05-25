package ru.tstst.schoolboy.domain.journal

import javax.inject.Inject

class MarkDigestAsViewedUC @Inject constructor(
    private val repository: JournalRepository
) {
    suspend fun execute(digestId: String) {
        repository.markDigestAsViewed(digestId)
    }
}