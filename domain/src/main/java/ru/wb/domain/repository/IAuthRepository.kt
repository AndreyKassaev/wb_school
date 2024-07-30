package ru.wb.domain.repository

import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    fun requestPinCode(): Flow<String>

    fun validatePinCode(pinCode: String): Flow<Boolean>

}