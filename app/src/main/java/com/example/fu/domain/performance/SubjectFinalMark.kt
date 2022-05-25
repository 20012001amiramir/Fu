package ru.tstst.schoolboy.domain.performance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.tstst.schoolboy.domain.profile.StudyClass
import ru.tstst.schoolboy.domain.subject.Subject

@JsonClass(generateAdapter = true)
data class SubjectFinalMarkWithClass(
    @Json(name = "studyClass") val studyClass: StudyClass,
    @Json(name = "subjectFinalMarks") val subjectFinalMarks: List<SubjectFinalMark>
)

@JsonClass(generateAdapter = true)
data class SubjectFinalMark(
    @Json(name = "subject") val subject: Subject,
    @Json(name = "yearMark") val yearMark: MarkAcademicTerm?,
    @Json(name = "markExam") val markExam: MarkExam?,
    @Json(name = "markReexam") val markReexam: MarkExam?,
    @Json(name = "finalMark") val finalMark: MarkAcademicTerm?
)