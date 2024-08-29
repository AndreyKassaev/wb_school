package ru.wb.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.wb.data.datasource.IDataSource
import ru.wb.data.jwt.DataStore
import ru.wb.data.jwt.EncryptedDataStore
import ru.wb.data.jwt.JwtPayload
import ru.wb.data.model.toDataProfile
import ru.wb.data.model.toDomainProfile
import ru.wb.domain.model.Profile
import ru.wb.domain.repository.IProfileRepository
import kotlin.io.encoding.ExperimentalEncodingApi

typealias DataProfile = ru.wb.data.model.Profile

@OptIn(ExperimentalEncodingApi::class)
internal class ProfileRepository(
    private val dataSource: IDataSource,
    private val encryptedDataStore: EncryptedDataStore,
    private val dataStore: DataStore
): IProfileRepository {

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
//    override fun createProfile(profile: Profile): Flow<Boolean> =
//        flow {
//            emit(
//                dataSource.setProfile(profile = profile)
//            )
//        }

    override fun updateProfile(profile: Profile): Flow<Boolean> =
        flow {
            emit(
                dataSource.setProfile(profile = profile)
            )
        }

    override fun getProfileById(profileId: String): Flow<Profile> =
        getProfileFromDataStore()

    override fun saveProfileImage(imageUrl: String) {
        scope.launch {
            dataStore.setProfileImage(
                imageUrl = imageUrl
            )
        }
    }

    override fun getProfileImage() =
        dataStore.getProfileImage()


    override suspend fun createProfile(profile: Profile) {
//        encryptedDataStore.createJwtPayload(
//            profile = profile
//        )
//        val jwt = Base64.encode(
//            Json.encodeToString(profile.toDataProfile()).toByteArray()
//        )
        val jwt = Json.encodeToString(profile.toDataProfile())
        dataStore.setJwtoken(
            jwtoken = jwt
        )
    }

    fun getProfileFromDataStore(): Flow<Profile> =
//        println()
//        return flowOf(
//            Profile.default
//        )
//        encryptedDataStore.getJwtPayload().map { it.toProfile() }
        dataStore.getJwtoken().map { jwt ->
            Json.decodeFromString<DataProfile>(jwt).toDomainProfile()
        }

}

private fun JwtPayload.toProfile(): Profile =
    Profile(
        id = this.profileId,
        firstName = this.firstName,
        lastName = this.lastName,
        imageUrl = this.imageUrl,
        phoneNumber = this.phoneNumber
    )

private fun Profile.toJwtPayload(): JwtPayload =
    JwtPayload(
        profileId = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        imageUrl = this.imageUrl,
        phoneNumber = this.phoneNumber,
        expiresAt = System.currentTimeMillis()
    )