package ru.wildberries.ui.screen.auth.pincode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import ru.wb.domain.usecase.auth.ValidatePinCodeUseCase
import ru.wildberries.ui.UIKit.molecule.PIN_CODE_LENGTH

internal class PinCodeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val validatePinCodeUseCase: ValidatePinCodeUseCase
): ViewModel() {

    private val phoneNumberMutable = MutableStateFlow(savedStateHandle["phone_number"] ?: "")
    private val phoneNumber: StateFlow<String> = phoneNumberMutable

    private val pinCodeMutable = MutableStateFlow( "")
    private val pinCode: StateFlow<String> = pinCodeMutable

    fun getPhoneNumberFlow() = phoneNumber

    fun getPinCodeFlow() = pinCode

    fun getIsPinCodeValidFlow(): Flow<Boolean> {
        if (pinCode.value.length != PIN_CODE_LENGTH) return flowOf(false)
        return validatePinCodeUseCase(pinCode = pinCode.value)
    }

    fun setVerificationPinCode(pinCode: String){
        pinCodeMutable.update {
            pinCode
        }
    }

}