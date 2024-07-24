package ru.wb.domain.usecase.community

import ru.wb.domain.repository.ICommunityRepository

class GetAllCommunityListUSeCase(
    private val communityRepository: ICommunityRepository
) {

    suspend operator fun invoke() =
        communityRepository.getAllCommunityList()

}