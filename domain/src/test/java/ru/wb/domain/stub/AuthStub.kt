package ru.wb.domain.stub

import kotlinx.coroutines.flow.flowOf

class AuthStub {

    val pinCode = "1234"

    fun requestPinCode() =
        flowOf("1234")

    fun validatePinCode(pinCode: String) =
        flowOf(
            pinCode.matches(Regex("""\d{4}"""))
        )

}