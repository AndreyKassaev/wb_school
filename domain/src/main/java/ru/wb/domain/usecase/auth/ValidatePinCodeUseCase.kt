package ru.wb.domain.usecase.auth

import ru.wb.domain.repository.IAuthRepository

interface ValidatePinCodeUseCase {

    suspend operator fun invoke(pinCode: String): Boolean

}

internal class ValidatePinCodeInteractor(
    private val authRepository: IAuthRepository
): ValidatePinCodeUseCase {

    override suspend operator fun invoke(pinCode: String) =
        authRepository.validatePinCode(pinCode = pinCode)

}