package ru.wildberries.ui.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.wildberries.domain.model.Profile
import ru.wildberries.domain.Repository

class ProfileViewModel(
    private val repository: Repository
): ViewModel() {
    private var _profile by mutableStateOf(Profile.default)
    val profile: Profile
        get() = _profile

    private fun getProfileData() {
        _profile = repository.getProfileData()
    }

    init {
        getProfileData()
    }
}