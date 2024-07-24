package ru.wb.domain.usecase.community

import ru.wb.domain.repository.ICommunityRepository

class GetCommunityEventListUSeCase(
    private val communityRepository: ICommunityRepository
) {

    suspend operator fun invoke(communityId: String) =
        communityRepository.getCommunityEventList(communityId = communityId)

}