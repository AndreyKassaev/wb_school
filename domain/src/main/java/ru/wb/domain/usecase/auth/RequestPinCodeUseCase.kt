package ru.wb.domain.usecase.auth

import ru.wb.domain.repository.IAuthRepository

interface RequestPinCodeUseCase{

    suspend operator fun invoke(): String

}

internal class RequestPinCodeInteractor(
    private val authRepository: IAuthRepository
): RequestPinCodeUseCase {

    override suspend operator fun invoke() =
        authRepository.requestPinCode()

}