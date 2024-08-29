package ru.wb.data.jwt

import kotlinx.serialization.Serializable

@Serializable
data class JwtPayload(
    val profileId: String,
    val firstName: String,
    val lastName: String?,
    val phoneNumber: String,
    val imageUrl: String?,
    val expiresAt: Long
) {
    companion object {
        val default = JwtPayload(
            profileId = "",
            firstName = "",
            lastName = "",
            phoneNumber = "",
            imageUrl = "",
            expiresAt = 0L
        )
    }
}