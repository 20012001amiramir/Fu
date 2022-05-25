package ru.tstst.schoolboy.domain.subject

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Subject(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
}