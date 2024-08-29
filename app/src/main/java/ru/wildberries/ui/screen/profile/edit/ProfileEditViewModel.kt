package ru.wildberries.ui.screen.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.profile.GetProfileImageUseCase
import ru.wb.domain.usecase.profile.SaveProfileImageUseCase

internal class ProfileEditViewModel(
    val saveProfileImageUseCase: SaveProfileImageUseCase,
    val getProfileImageUseCase: GetProfileImageUseCase
) : ViewModel() {

    private val profileImageUrlMutable = MutableStateFlow("")
    val profileImageUrl: StateFlow<String> = profileImageUrlMutable

    init {
        initProfileImageUrl()
    }

    fun getProfileImageUrlFlow() = profileImageUrl

    fun saveProfileImage(imageUrl: String) {
        saveProfileImageUseCase(imageUrl = imageUrl)
    }

    private fun initProfileImageUrl() {
        viewModelScope.launch {
            getProfileImageUseCase().collectLatest { imageUrl ->
                profileImageUrlMutable.update {
                    imageUrl
                }
            }
        }
    }

}