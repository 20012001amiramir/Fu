package ru.tstst.schoolboy.domain.journal

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import ru.tstst.schoolboy.ui.common.list.RecyclerItem

@JsonClass(generateAdapter = true)
open class DigestCard(
    @Json(name = "type") val type: DigestCardType,
    @Json(name = "id") val id: String,
    @Json(name = "image") val image: String,
    @Json(name = "previewImage") val previewImage: String,
    @Json(name = "title") val title: String
) : RecyclerItem