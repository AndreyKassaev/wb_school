package ru.wb.domain.stub

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.wb.domain.model.Community

class CommunityStub {

    val communityId = "1"

    fun getCommunityById(communityId: String): Flow<Community> =
        flowOf(
            Community.default.copy(
                id = communityId
            )
        )

    fun getAllCommunityList(): Flow<List<Community>> =
        flowOf(
            List(10) { Community.default.copy(id = communityId) }
        )


}