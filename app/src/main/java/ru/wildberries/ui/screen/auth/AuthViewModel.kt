package ru.wildberries.ui.screen.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.wildberries.domain.model.Profile
import ru.wildberries.domain.Repository

class AuthViewModel(
    private val repository: Repository
) : ViewModel() {

    private var _verificationPhoneNumber by mutableStateOf("")
    val verificationPhoneNumber: String
        get() = _verificationPhoneNumber

    private var _verificationPhoneNumberCountryCode by mutableStateOf("+7")
    val verificationPhoneNumberCountryCode: String
        get() = _verificationPhoneNumberCountryCode

    private var _verificationPinCode by mutableStateOf("")
    val verificationPinCode: String
        get() = _verificationPinCode

    fun setVerificationPhoneNumber(phoneNumber: String) {
        _verificationPhoneNumber = phoneNumber
    }

    fun setVerificationPhoneNumberCountryCode(countryCode: String) {
        _verificationPhoneNumberCountryCode = countryCode
    }

    fun setVerificationPinCode(pinCode: String){
        _verificationPinCode = pinCode
    }

    fun setProfile(
        firstName: String,
        lastName: String = ""
    ) {
        viewModelScope.launch {
            repository.setProfileData(
                profile = Profile.default.copy(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = "$verificationPhoneNumberCountryCode$verificationPhoneNumber"
                )
            )
        }
    }
}