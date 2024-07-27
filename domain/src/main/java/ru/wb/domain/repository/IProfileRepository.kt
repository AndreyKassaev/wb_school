package ru.wb.domain.repository

import ru.wb.domain.model.Profile

interface IProfileRepository {

    suspend fun createProfile(profile: Profile): Boolean

    suspend fun updateProfile(profile: Profile)

    suspend fun getProfileById(profileId: String): Profile

}