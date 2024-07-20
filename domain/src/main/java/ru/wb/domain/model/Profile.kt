package ru.wb.domain.model

import java.util.UUID

data class Profile(
    val id: String,
    val firstName: String,
    val lastName: String,
    val imageUrl: String?,
    val phoneNumber: String
){
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
