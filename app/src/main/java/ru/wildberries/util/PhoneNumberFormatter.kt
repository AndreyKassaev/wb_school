package ru.wildberries.util

fun phoneNumberFormatter(phoneNumber: String): String {
    val regex = """(\d)(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
    val output = regex.replace(phoneNumber, "$1 $2 $3-$4-$5")
    return output
}