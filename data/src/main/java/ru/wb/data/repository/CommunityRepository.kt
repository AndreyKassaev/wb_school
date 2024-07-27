package ru.wb.data.repository

import ru.wb.data.datasource.IDataSource
import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.repository.ICommunityRepository

internal class CommunityRepository(
    private val dataSource: IDataSource
): ICommunityRepository {

    override suspend fun getCommunityById(communityId: String): Community =
        dataSource.getCommunityById(communityId = communityId)

    override suspend fun getCommunityEventList(communityId: String): List<Event> =
        dataSource.getEventList(communityId = communityId)

    override suspend fun getAllCommunityList(): List<Community> =
        dataSource.getCommunityList()

}