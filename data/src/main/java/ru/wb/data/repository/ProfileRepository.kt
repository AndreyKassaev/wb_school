package ru.wb.data.repository

import ru.wb.data.datasource.IDataSource
import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

internal class ProfileRepository(
    private val dataSource: IDataSource
): IProfileRepository {

    override suspend fun createProfile(profile: Profile) {
        dataSource.setProfile(profile = profile)
    }

    override suspend fun updateProfile(profile: Profile) {
        dataSource.setProfile(profile = profile)
    }

    override suspend fun getProfileById(profileId: String) =
        dataSource.getProfile()

}