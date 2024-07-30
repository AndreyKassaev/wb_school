package ru.wb.data.datasource

import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.model.Profile

internal interface IDataSource {

    suspend fun getPinCode(): String

    suspend fun validatePinCode(pinCode: String): Boolean

    suspend fun getCommunityList(): List<Community>

    suspend fun getCommunityById(communityId: String): Community

    suspend fun getEventList(): List<Event>

    suspend fun getEventById(eventId: String): Event

    suspend fun getEventList(communityId: String): List<Event>

    suspend fun getProfile(): Profile

    suspend fun setProfile(profile: Profile): Boolean

    suspend fun addUserToEventVisitorList(eventId: String): Event

    suspend fun removeUserFromEventVisitorList(eventId: String): Event

}