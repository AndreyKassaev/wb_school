package ru.wb.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.repository.IAuthRepository

interface RequestPinCodeUseCase{

    operator fun invoke(): Flow<String>

}

internal class RequestPinCodeInteractor(
    private val authRepository: IAuthRepository
): RequestPinCodeUseCase {

    override operator fun invoke() =
        authRepository.requestPinCode()

}