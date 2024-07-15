package ru.wildberries.domain

import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getProfileData(): ProfileModel

    fun getEventList(): Flow<List<EventModel>>

    fun getCommunityList(): Flow<List<CommunityModel>>

    fun getEventVisitorList(): Flow<List<ProfileModel>>

    fun setEventVisitorList(visitor: ProfileModel): Flow<List<ProfileModel>>

    fun setEventVisitorList(): Flow<List<ProfileModel>>

}