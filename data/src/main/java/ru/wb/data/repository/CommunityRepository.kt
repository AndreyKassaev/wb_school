package ru.wb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wb.data.datasource.IDataSource
import ru.wb.domain.model.Community
import ru.wb.domain.repository.ICommunityRepository

internal class CommunityRepository(
    private val dataSource: IDataSource
): ICommunityRepository {

    override fun getCommunityById(communityId: String): Flow<Community> =
        flow {
            emit(
                dataSource.getCommunityById(communityId = communityId)
            )
        }

    override fun getAllCommunityList(): Flow<List<Community>> =
        flow {
            emit(
                dataSource.getCommunityList()
            )
        }


}