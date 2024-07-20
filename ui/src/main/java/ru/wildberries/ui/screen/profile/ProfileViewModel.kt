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

    private var profileMutable = MutableStateFlow(Profile.default)
    private val profile = profileMutable.asStateFlow()

    fun getProfileFlow() = profile

    private fun initProfileData() {
        profileMutable.update {
            repository.getProfileData()
        }
    }

    init {
        initProfileData()
    }
}