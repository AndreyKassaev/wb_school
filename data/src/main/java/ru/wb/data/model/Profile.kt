package ru.wb.data.model

import kotlinx.serialization.Serializable
import java.util.UUID

typealias DomainProfile = ru.wb.domain.model.Profile

@Serializable
data class Profile(
    val id: String,
    val firstName: String,
    val lastName: String?,
    val imageUrl: String?,
    val phoneNumber: String
) {
    companion object {
        val default = Profile(
            id = UUID.randomUUID().toString(),
            firstName = "FirstName",
            lastName = "LastName",
            imageUrl = "https://kassaev.com/media/night_sky.jpg",
            phoneNumber = "+79967370744"
        )
    }
}

fun DomainProfile.toDataProfile(): Profile =
    Profile(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        imageUrl = this.imageUrl,
        phoneNumber = this.phoneNumber
    )

fun Profile.toDomainProfile(): DomainProfile =
    DomainProfile(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        imageUrl = this.imageUrl,
        phoneNumber = this.phoneNumber
    )
