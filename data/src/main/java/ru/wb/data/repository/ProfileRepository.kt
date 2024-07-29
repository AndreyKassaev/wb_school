package ru.wb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wb.data.datasource.IDataSource
import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository

internal class ProfileRepository(
    private val dataSource: IDataSource
): IProfileRepository {

    override fun createProfile(profile: Profile): Flow<Boolean> =
        flow {
            emit(
                dataSource.setProfile(profile = profile)
            )
        }

    override fun updateProfile(profile: Profile): Flow<Boolean> =
        flow {
            emit(
                dataSource.setProfile(profile = profile)
            )
        }

    override fun getProfileById(profileId: String): Flow<Profile> =
        flow {
            emit(
                dataSource.getProfile()
            )
        }

}