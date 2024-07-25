package ru.wildberries.ui.screen.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.profile.GetProfileByIdUseCase
import ru.wildberries.ui.model.Profile
import ru.wildberries.ui.model.toUiProfile

class MoreViewModel(
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): ViewModel() {

    private var profileMutable = MutableStateFlow(Profile.default)
    private val profile: StateFlow<Profile> = profileMutable

    init {
        initProfileData()
    }

    internal fun getProfileFlow() = profile

    private fun initProfileData() {
        viewModelScope.launch {
            profileMutable.update {
                getProfileByIdUseCase(profileId = "").toUiProfile()
            }
        }
    }

}