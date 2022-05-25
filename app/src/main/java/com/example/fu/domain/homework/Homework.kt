package ru.tstst.schoolboy.domain.homework

import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class Homework(
    @Json(name = "id") val id: String,
    @Json(name = "lessonName") val lessonName: String,
    @Json(name = "note") var note: String?,
    @Json(name = "text") val text: String,
    @Json(name = "deadline") val deadline: LocalDateTime,
    @Json(name = "completed") var completed: Boolean = false
) {

    val textEscapeHtml: Spanned = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}
