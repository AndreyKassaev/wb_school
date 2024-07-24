package ru.wb.data.repository

import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

internal class ProfileRepository: IProfileRepository {

    private var profile = Profile.default

    override suspend fun createProfile(profile: Profile) {
        this.profile = profile
    }

    override suspend fun updateProfile(profile: Profile) {
        this.profile = profile
    }

    override suspend fun getProfileById(profileId: String) =
        this.profile

}