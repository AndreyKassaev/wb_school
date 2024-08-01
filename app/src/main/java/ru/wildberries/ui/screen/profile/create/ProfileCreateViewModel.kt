package ru.wildberries.ui.screen.profile.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.profile.CreateProfileUseCase
import ru.wildberries.ui.model.Profile
import ru.wildberries.ui.model.toDomainProfile
import ru.wildberries.util.VerifyPinCodeNotificationService
import java.util.UUID

internal class ProfileCreateViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val createProfileUseCase: CreateProfileUseCase,
    private val verifyPinCodeNotificationService: VerifyPinCodeNotificationService
): ViewModel() {

    init {
        verifyPinCodeNotificationService.cancelAllNotifications()
    }

    fun createProfile(
        firstName: String,
        lastName: String = ""
    ) {
        viewModelScope.launch {
            createProfileUseCase(
                profile = Profile(
                    id = UUID.randomUUID().toString(),
                    firstName = firstName,
                    lastName = lastName,
                    imageUrl = null,
                    phoneNumber = savedStateHandle.get<String>("phone_number") ?: ""
                ).toDomainProfile()
            ).collectLatest { }
        }
    }

}