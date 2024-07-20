package ru.wildberries.ui.model

import ru.wildberries.R

data class PhoneNumber(
    val countryCode: PhoneCountryCode,
    val number: String
){
    companion object {
        val default = PhoneNumber(
            countryCode = PhoneCountryCode.default,
            number = ""
        )
    }
}

data class PhoneCountryCode(
    val code: String,
    val icon: Int
){
    companion object {
        val default = PhoneCountryCode(
            code = "+7",
            icon = R.drawable.ru_flag
        )
    }
}