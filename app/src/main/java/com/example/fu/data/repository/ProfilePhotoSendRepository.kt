package ru.tstst.schoolboy.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import com.example.fu.data.network.Api
import java.io.File
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ProfilePhotoSendRepository @Inject constructor(
    private val api: Api
) {

    suspend fun sendProfilePhoto(image: File): Boolean {
        val requestFile: RequestBody =
            image.asRequestBody("image/jpeg".toMediaTypeOrNull())

        return api.photoUpload(MultipartBody.Part.createFormData("photo", image.name, requestFile))
    }

    suspend fun removePhoto(): Boolean {
        return api.removePhoto()
    }

}