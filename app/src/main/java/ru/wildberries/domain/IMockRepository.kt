package ru.wildberries.domain

import kotlinx.coroutines.flow.Flow

interface IMockRepository {

    fun getProfileData(): ProfileModel

    fun getEventList(): Flow<List<EventModel>>

    fun getCommunityList(): Flow<List<CommunityModel>>

    fun getEventVisitorList(): Flow<List<ProfileModel>>
}