package ru.wb.domain.usecase.profile

import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

interface GetProfileByIdUseCase {

    suspend operator fun invoke(profileId: String): Profile
}

internal class GetProfileByIdInteractor(
    private val profileRepository: IProfileRepository
): GetProfileByIdUseCase {

    override suspend operator fun invoke(profileId: String) =
        profileRepository.getProfileById(profileId = profileId)

}