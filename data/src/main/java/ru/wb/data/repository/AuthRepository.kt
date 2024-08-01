package ru.wb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wb.data.datasource.IDataSource
import ru.wb.domain.repository.IAuthRepository

internal class AuthRepository(
    private val dataSource: IDataSource
): IAuthRepository {

    val PIN_CODE_LENGTH = 4

    override fun requestPinCode(): Flow<String> =
        flow {
            emit(
                dataSource.getPinCode()
            )
        }

    override fun validatePinCode(pinCode: String): Flow<Boolean> =
        flow {
            emit(
                if (pinCode.length == PIN_CODE_LENGTH) {
                    dataSource.validatePinCode(pinCode = pinCode)
                } else {
                    false
                }
            )
        }

}