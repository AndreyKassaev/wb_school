package ru.wb.data.jwt

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.wb.domain.model.Profile
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class EncryptedDataStore(
    private val context: Context,
    private val cryptoManager: CryptoManager
) {
    private val Context.encryptedDataStore by dataStore(
        fileName = "encryptedJwt10.json",
        serializer = JwtSerializer(cryptoManager)
    )

    suspend fun createJwtPayload(profile: Profile) {
        context.encryptedDataStore.updateData {
            JwtPayload(
                profileId = profile.id,
                firstName = profile.firstName,
                lastName = profile.lastName,
                phoneNumber = profile.phoneNumber,
                imageUrl = profile.imageUrl,
                expiresAt = System.currentTimeMillis()
            )
        }
    }

    fun getJwtPayload(): Flow<JwtPayload> =
        context.encryptedDataStore.data

}

@OptIn(ExperimentalEncodingApi::class)
private fun jwtData(profile: Profile): String {
    return Base64.encode(
        Json.encodeToString<Profile>(profile).encodeToByteArray()
    )
}