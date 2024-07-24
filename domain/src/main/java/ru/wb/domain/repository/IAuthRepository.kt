package ru.wb.domain.repository

interface IAuthRepository {

    suspend fun requestPinCode(): String

    suspend fun validatePinCode(pinCode: String): Boolean

}