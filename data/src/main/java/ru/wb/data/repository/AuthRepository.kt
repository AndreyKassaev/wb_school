package ru.wb.data.repository

import ru.wb.data.datasource.IDataSource
import ru.wb.domain.repository.IAuthRepository

internal class AuthRepository(
    private val dataSource: IDataSource
): IAuthRepository {

    override suspend fun requestPinCode(): String =
        dataSource.getPinCode()

    override suspend fun validatePinCode(pinCode: String): Boolean =
//        pinCode == this.pinCode
        true

}