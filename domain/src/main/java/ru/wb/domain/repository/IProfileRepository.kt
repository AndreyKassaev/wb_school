package ru.wb.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Profile

interface IProfileRepository {

    fun createProfile(profile: Profile): Flow<Boolean>

    fun updateProfile(profile: Profile): Flow<Boolean>

    fun getProfileById(profileId: String): Flow<Profile>

}