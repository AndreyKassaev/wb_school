package ru.wb.domain.usecase.community

import ru.wb.domain.repository.ICommunityRepository

class GetCommunityByIdUseCase(
    private val communityRepository: ICommunityRepository
) {

    suspend operator fun invoke(communityId: String) =
        communityRepository.getCommunityById(communityId = communityId)

}