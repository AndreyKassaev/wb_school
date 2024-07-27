package ru.wb.domain.usecase.community

import ru.wb.domain.model.Community
import ru.wb.domain.repository.ICommunityRepository

interface GetAllCommunityListUseCase {

    suspend operator fun invoke(): List<Community>

}

internal class GetAllCommunityListInteractor(
    private val communityRepository: ICommunityRepository
): GetAllCommunityListUseCase {

    override suspend operator fun invoke() =
        communityRepository.getAllCommunityList()

}