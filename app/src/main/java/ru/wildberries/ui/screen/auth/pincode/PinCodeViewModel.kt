package ru.wildberries.ui.screen.auth.pincode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.auth.RequestPinCodeUseCase
import ru.wb.domain.usecase.auth.ValidatePinCodeUseCase
import ru.wildberries.util.VerifyPinCodeNotificationService

internal class PinCodeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val validatePinCodeUseCase: ValidatePinCodeUseCase,
    private val requestPinCodeUseCase: RequestPinCodeUseCase,
    private val verifyPinCodeNotificationService: VerifyPinCodeNotificationService
): ViewModel() {

    private val phoneNumberMutable = MutableStateFlow(savedStateHandle["phone_number"] ?: "")
    private val phoneNumber: StateFlow<String> = phoneNumberMutable

    private val pinCodeMutable = MutableStateFlow( "")
    private val pinCode: StateFlow<String> = pinCodeMutable

    init {
        requestPinCode()
    }

    fun getPhoneNumberFlow() = phoneNumber

    fun getPinCodeFlow() = pinCode

    fun getIsPinCodeValidFlow(): Flow<Boolean> = validatePinCodeUseCase(pinCode = pinCode.value)

    fun setVerificationPinCode(pinCode: String){
        pinCodeMutable.update {
            pinCode
        }
    }

    fun requestPinCode() {
        viewModelScope.launch {
            requestPinCodeUseCase().collect { pinCode ->
                verifyPinCodeNotificationService.showNotification(pinCode = pinCode)
            }
        }
    }

}