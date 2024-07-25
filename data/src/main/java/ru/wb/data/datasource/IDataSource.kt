package ru.wb.data.datasource

import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.model.EventVisitor
import ru.wb.domain.model.Profile

internal interface IDataSource {

    suspend fun getPinCode(): String

    suspend fun getCommunityList(): List<Community>

    suspend fun getCommunityById(communityId: String): Community

    suspend fun getEventList(): List<Event>

    suspend fun getEventById(eventId: String): Event

    suspend fun getEventList(communityId: String): List<Event>

    suspend fun getEventVisitorList(): List<EventVisitor>

    suspend fun getProfile(): Profile

    suspend fun setProfile(profile: Profile)

    suspend fun addUserToEventVisitorList(eventId: String): List<EventVisitor>

    suspend fun removeUserFromEventVisitorList(eventId: String): List<EventVisitor>

}