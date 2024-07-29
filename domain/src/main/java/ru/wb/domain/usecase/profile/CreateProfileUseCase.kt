package ru.wb.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

interface CreateProfileUseCase {

    suspend operator fun invoke(profile: Profile): Flow<Boolean>

}

internal class CreateProfileInteractor(
    private val profileRepository: IProfileRepository
): CreateProfileUseCase {

    override suspend operator fun invoke(profile: Profile) =
        profileRepository.createProfile(profile = profile)

}