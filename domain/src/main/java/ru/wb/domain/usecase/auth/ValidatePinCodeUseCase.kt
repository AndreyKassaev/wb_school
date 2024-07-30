package ru.wb.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.repository.IAuthRepository

interface ValidatePinCodeUseCase {

    operator fun invoke(pinCode: String): Flow<Boolean>

}

internal class ValidatePinCodeInteractor(
    private val authRepository: IAuthRepository
): ValidatePinCodeUseCase {

    override operator fun invoke(pinCode: String) =
        authRepository.validatePinCode(pinCode = pinCode)

}