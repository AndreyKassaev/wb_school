package ru.wb.domain.usecase.profile

import ru.wb.domain.repository.IProfileRepository

class GetProfileByIdUseCase(
    private val profileRepository: IProfileRepository
) {

    suspend operator fun invoke(profileId: String) =
        profileRepository.getProfileById(profileId = profileId)

}