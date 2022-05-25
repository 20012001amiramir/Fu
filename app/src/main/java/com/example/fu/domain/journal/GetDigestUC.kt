package ru.tstst.schoolboy.domain.journal

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDigestUC @Inject constructor(
    private val repository: JournalRepository
) {
    fun execute(digestId: String): Flow<Digest?> = repository.getDigestFlow(digestId)
}