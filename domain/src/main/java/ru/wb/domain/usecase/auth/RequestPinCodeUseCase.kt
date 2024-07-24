package ru.wb.domain.usecase.auth

import ru.wb.domain.repository.IAuthRepository

class RequestPinCodeUseCase(
    private val authRepository: IAuthRepository
) {

    suspend operator fun invoke() =
        authRepository.requestPinCode()

}