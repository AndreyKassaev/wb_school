package ru.wildberries.ui.screen.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.wb.domain.Repository
import ru.wb.domain.model.Profile

class ProfileViewModel(
    private val repository: Repository
): ViewModel() {

    private var _profile = MutableStateFlow(Profile.default)
    val profile = _profile.asStateFlow()

    private fun getProfileData() {
        _profile.update {
            repository.getProfileData()
        }
    }

    init {
        getProfileData()
    }
}