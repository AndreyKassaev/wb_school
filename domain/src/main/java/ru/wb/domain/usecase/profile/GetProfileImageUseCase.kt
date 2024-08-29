package ru.wb.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.repository.IProfileRepository

interface GetProfileImageUseCase {

    operator fun invoke(): Flow<String>

}

internal class GetProfileImageUseCaseImpl(
    private val profileRepository: IProfileRepository
) : GetProfileImageUseCase {

    override operator fun invoke() =
        profileRepository.getProfileImage()

}