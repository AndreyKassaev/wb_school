package ru.wb.domain.repository

import ru.wb.domain.model.Community
import ru.wb.domain.model.Event

interface ICommunityRepository {

    suspend fun getCommunityById(communityId: String): Community

    suspend fun getCommunityEventList(communityId: String): List<Event>

    suspend fun getAllCommunityList(): List<Community>

}