package ru.wildberries.domain

import ru.wildberries.R

data class ProfileModel(
    val image: Int = R.drawable.android,
    val firstName: String = "FirstName",
    val lastName: String = "LastName",
    val phoneNumber: String = "+9 999 999-99-99"
)
