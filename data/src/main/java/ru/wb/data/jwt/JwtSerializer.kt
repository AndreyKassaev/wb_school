package ru.wb.data.jwt

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class JwtSerializer(
    private val cryptoManager: CryptoManager
) : Serializer<JwtPayload> {

    override val defaultValue: JwtPayload
        get() = JwtPayload.default

    override suspend fun readFrom(input: InputStream): JwtPayload {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = JwtPayload.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: JwtPayload,
        output: OutputStream
    ) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = JwtPayload.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}