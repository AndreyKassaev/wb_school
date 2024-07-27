package ru.wb.domain.usecase.community

import ru.wb.domain.model.Event
import ru.wb.domain.repository.ICommunityRepository

interface GetCommunityEventListUseCase {

    suspend operator fun invoke(communityId: String): List<Event>
}

internal class GetCommunityEventListInteractor(
    private val communityRepository: ICommunityRepository
): GetCommunityEventListUseCase {

    override suspend operator fun invoke(communityId: String) =
        communityRepository.getCommunityEventList(communityId = communityId)

}