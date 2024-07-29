package ru.wb.domain.stub

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.wb.domain.model.Profile

class ProfileStub {

    val profile = Profile.default.copy(id = "1")

    fun createProfile(profile: Profile): Flow<Boolean> =
        flowOf(
            true
        )


    fun getProfileById(profileId: String): Flow<Profile> =
        flowOf(
            profile
        )

}