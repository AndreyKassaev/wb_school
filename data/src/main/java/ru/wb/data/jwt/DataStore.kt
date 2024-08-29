package ru.wb.data.jwt

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ru.wb.data.jwt.DataStore.PreferenceKey.jwtokenKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.wb.data.jwt.DataStore.PreferenceKey.profileImage

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "local_storage")

class DataStore(
    private val context: Context
) {

    private object PreferenceKey {
        val jwtokenKey: Preferences.Key<String> = stringPreferencesKey("jwtoken")
        val profileImage: Preferences.Key<String> = stringPreferencesKey("profileImage")
    }

    suspend fun setJwtoken(jwtoken: String) =
        context.dataStore.edit { preferences: MutablePreferences ->
            preferences[jwtokenKey] = jwtoken
        }

    fun getJwtoken(): Flow<String> =
        context.dataStore.data.map { preferences: Preferences ->
            return@map preferences[jwtokenKey] ?: ""
        }

    suspend fun setProfileImage(imageUrl: String) {
        context.dataStore.edit { preferences: MutablePreferences ->
            preferences[profileImage] = imageUrl
        }
    }

    fun getProfileImage(): Flow<String> =
        context.dataStore.data.map { preferences: Preferences ->
            return@map preferences[profileImage] ?: ""
        }


}