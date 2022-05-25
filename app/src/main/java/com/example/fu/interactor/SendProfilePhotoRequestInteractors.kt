package ru.tstst.schoolboy.interactor

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.tstst.schoolboy.data.repository.ProfilePhotoSendRepository
import java.io.File
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class SendProfilePhotoRequestInteractors
@Inject constructor(
    private val repository: ProfilePhotoSendRepository
) {

    suspend fun sendProfilePhoto(image: File): Boolean {
        return repository.sendProfilePhoto(image)
    }

    suspend fun removePhoto(): Boolean{
        return repository.removePhoto()
    }

}