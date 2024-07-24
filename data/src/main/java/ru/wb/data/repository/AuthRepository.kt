package ru.wb.data.repository

import ru.wb.domain.repository.IAuthRepository
import kotlin.random.Random

internal class AuthRepository: IAuthRepository {

    private val pinCode = Random.nextInt(from = 1000, until = 9999).toString()

    override suspend fun requestPinCode(): String =
        pinCode

    override suspend fun validatePinCode(pinCode: String): Boolean =
//        pinCode == this.pinCode
        true
}