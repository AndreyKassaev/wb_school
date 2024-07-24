package ru.wildberries.ui.screen.auth.pincode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import ru.wb.domain.usecase.auth.ValidatePinCodeUseCase
import ru.wildberries.ui.UIKit.molecule.PIN_CODE_LENGTH

class PinCodeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val validatePinCodeUseCase: ValidatePinCodeUseCase
): ViewModel() {

    private val phoneNumberMutable = MutableStateFlow(savedStateHandle["phone_number"] ?: "")
    private val phoneNumber: StateFlow<String> = phoneNumberMutable

    private val pinCodeMutable = MutableStateFlow( "")
    private val pinCode: StateFlow<String> = pinCodeMutable

    private val isPinCodeValid = combine(pinCodeMutable){ currentPinCode ->
        if (currentPinCode.last().length == PIN_CODE_LENGTH){
            validatePinCodeUseCase(pinCode = pinCode.value)
        }else{
            false
        }
    }

    fun getPhoneNumberFlow() = phoneNumber

    fun getPinCodeFlow() = pinCode

    fun getIsPinCodeValid() = isPinCodeValid

    fun setVerificationPinCode(pinCode: String){
        pinCodeMutable.update {
            pinCode
        }
    }

}