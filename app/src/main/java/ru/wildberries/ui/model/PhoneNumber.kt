package ru.wildberries.ui.model

import ru.wildberries.R

internal data class PhoneNumber(
    val countryCode: CountryCode,
    val number: String
){
    companion object {
        val default = PhoneNumber(
            countryCode = CountryCode.default,
            number = ""
        )
    }
}

internal data class CountryCode(
    val code: String,
    val icon: Int
){
    companion object {
        val default = CountryCode(
            code = "+7",
            icon = R.drawable.ru_flag
        )
    }
}