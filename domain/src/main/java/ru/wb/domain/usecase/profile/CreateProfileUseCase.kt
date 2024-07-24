package ru.wb.domain.usecase.profile

import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

class CreateProfileUseCase(
    private val profileRepository: IProfileRepository
) {

    suspend operator fun invoke(profile: Profile) =
        profileRepository.createProfile(profile = profile)

}