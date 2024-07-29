package ru.wb.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Community

interface ICommunityRepository {

    fun getCommunityById(communityId: String): Flow<Community>

    fun getAllCommunityList(): Flow<List<Community>>

}