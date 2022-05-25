package ru.tstst.schoolboy.domain.story

import androidx.lifecycle.MutableLiveData
import ru.tstst.schoolboy.data.repository.ProfileRepository
import javax.inject.Inject

class HasArchive @Inject constructor(
    private val repository: ProfileRepository,
) {
    fun getArchive(): MutableLiveData<Boolean?> {
        return repository.isArchive()
    }
}