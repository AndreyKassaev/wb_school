package ru.wb.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Profile

interface IProfileRepository {

    suspend fun createProfile(profile: Profile)

    fun updateProfile(profile: Profile): Flow<Boolean>

    fun getProfileById(profileId: String): Flow<Profile>

    fun saveProfileImage(imageUrl: String)

    fun getProfileImage(): Flow<String>
}