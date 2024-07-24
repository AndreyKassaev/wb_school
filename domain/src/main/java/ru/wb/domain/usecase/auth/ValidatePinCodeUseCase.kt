package ru.wb.domain.usecase.auth

import ru.wb.domain.repository.IAuthRepository

class ValidatePinCodeUseCase(
    private val authRepository: IAuthRepository
) {

    suspend operator fun invoke(pinCode: String) =
        authRepository.validatePinCode(pinCode = pinCode)

}