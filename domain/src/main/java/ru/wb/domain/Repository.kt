package ru.wb.domain

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.model.Profile

interface Repository {

    fun getProfileData(): Profile

    fun setProfileData(profile: Profile)

    fun getEventList(): Flow<List<Event>>

    fun getCommunityList(): Flow<List<Community>>

    fun getEventVisitorList(): Flow<List<Profile>>

    fun setEventVisitorList(visitor: Profile): Flow<List<Profile>>

    fun setEventVisitorList(): Flow<List<Profile>>

}