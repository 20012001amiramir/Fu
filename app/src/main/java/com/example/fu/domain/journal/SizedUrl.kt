package ru.tstst.schoolboy.domain.journal

data class SizedUrl(
    private val rawUrl: String
) {
    fun getSizedUrl(width: Int, height: Int) = "$rawUrl?w=$width&h=$height"
}