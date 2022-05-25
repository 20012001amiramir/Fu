package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcademicTerms(
    @Json(name = "academicTerms") val list: List<AcademicTerm>
) {

    fun isEmpty(): Boolean = list.isEmpty()

    fun currentOrLastPast(): AcademicTerm {
        return list.firstOrNull { it.tense == Tense.CURRENT }
            ?: list.first { it.tense == Tense.PAST }
    }

    // TODO: (Discuss) Maybe return -1 instead of null, like in the library's indexOfFirst?
    fun indexOfCurrent(): Int? {
        val index = list.indexOfFirst { it.tense == Tense.CURRENT }
        return if (0 <= index) index else null
    }

    fun indexOfLastPast(): Int? {
        for (i in list.lastIndex downTo 0) {
            val academicTerm = list[i]
            val academicTermType = academicTerm.type
            if (academicTermType != AcademicTermType.YEAR && academicTermType != AcademicTermType.FINAL && academicTerm.tense == Tense.PAST) {
                return i
            }
        }
        return null
    }
}