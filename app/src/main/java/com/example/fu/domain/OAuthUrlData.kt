package ru.tstst.schoolboy.domain

import android.util.Base64
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

@JsonClass(generateAdapter = true)
data class OAuthUrlData(
    @Json(name = "codeVerifier") val codeVerifier: String,
    @Json(name = "codeChallenge") val codeChallenge: String,
    @Json(name = "state") val state: String,
    @Json(name = "oAuthBaseUrl") val oAuthBaseUrl: String = "edu.tatar.ru",
    @Json(name = "oAuthUrl") val oAuthUrl: String = "oauth/authorize",
    @Json(name = "clientId") val clientId: String = "9b328d23",
    @Json(name = "responseType") val responseType: String = "code",
    @Json(name = "redirectUri") val redirectUri: String = "rt.schoolboy.app://oauth2redirect",
    @Json(name = "codeChallengeMethod") val codeChallengeMethod: String = "S256"
) {

    companion object {

        @Suppress("FunctionName")
        fun OAuthUrlData(): OAuthUrlData {
            val codeVerifier = cryptoCodeVerifier()
            return OAuthUrlData(
                codeVerifier,
                cryptoCodeChallenge(codeVerifier),
                generateUUID()
            )
        }

        private fun cryptoCodeVerifier(): String {
            val length = 128
            val secureRandom = SecureRandom()
            val bytes = ByteArray(length)
            secureRandom.nextBytes(bytes)
            return Base64
                .encodeToString(bytes, Base64.DEFAULT)
                .replace("\n", "")
                .replace("=", "")
                .replace("+", "-")
                .replace("/", "_")
                .substring(0, 88)
        }

        private fun cryptoCodeChallenge(codeVerifier: String): String =
            Base64
                .encodeToString(
                    MessageDigest
                        .getInstance("SHA-256")
                        .digest(codeVerifier.toByteArray(Charsets.US_ASCII)),
                    Base64.DEFAULT
                )
                .replace("\n", "")
                .replace("=", "")
                .replace("+", "-")
                .replace("/", "_")

        private fun generateUUID() =
            UUID.randomUUID().toString().replace("-", "")
    }
}