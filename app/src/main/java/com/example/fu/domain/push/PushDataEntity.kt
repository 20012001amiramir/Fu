package ru.tstst.schoolboy.domain.push

data class PushDataEntity(
    val idMessage: Int?,
    val title: String,
    val body: String,
    val type: TypePush,
    val id: Int?
)

enum class TypePush {
    STORY,
    MARK,
    UNKNOWN
}