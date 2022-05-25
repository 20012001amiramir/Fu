package ru.tstst.schoolboy.domain

enum class Language(val tag: String) {
    RU("ru"),
    TT("tt")
}

fun createLanguage(tag: String) =
    when(tag) {
        "ru" -> Language.RU
        "tt" -> Language.TT
        else -> throw IllegalAccessException("Unsupported language tag")
    }
