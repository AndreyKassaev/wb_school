package ru.wb.domain.usecase.community

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Community
import ru.wb.domain.repository.ICommunityRepository

interface GetCommunityByIdUseCase {

    suspend operator fun invoke(communityId: String): Flow<Community>

}

internal class GetCommunityByIdInteractor(
    private val communityRepository: ICommunityRepository
): GetCommunityByIdUseCase {

    override suspend operator fun invoke(communityId: String) =
        communityRepository.getCommunityById(communityId = communityId)

}