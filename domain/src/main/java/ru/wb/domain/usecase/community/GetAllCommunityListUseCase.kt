package ru.wb.domain.usecase.community

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Community
import ru.wb.domain.repository.ICommunityRepository

interface GetAllCommunityListUseCase {

    operator fun invoke(): Flow<List<Community>>

}

internal class GetAllCommunityListInteractor(
    private val communityRepository: ICommunityRepository
): GetAllCommunityListUseCase {

    override operator fun invoke() =
        communityRepository.getAllCommunityList()

}