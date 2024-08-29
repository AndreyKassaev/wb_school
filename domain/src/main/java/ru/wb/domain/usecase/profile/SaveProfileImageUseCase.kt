package ru.wb.domain.usecase.profile

import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

interface SaveProfileImageUseCase {

    operator fun invoke(imageUrl: String)

}

internal class SaveProfileImageUseCaseImpl(
    private val profileRepository: IProfileRepository
) : SaveProfileImageUseCase {

    override operator fun invoke(imageUrl: String) =
        profileRepository.saveProfileImage(imageUrl = imageUrl)

}