package ru.wildberries.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.Repository
import ru.wb.domain.model.Profile
import ru.wildberries.R
import ru.wildberries.ui.model.PhoneCountryCode
import ru.wildberries.ui.model.PhoneNumber

const val PHONE_NUMBER_REQUIRED_LENGTH = 10

class AuthViewModel(
    private val repository: Repository
) : ViewModel() {

    val phoneCountryCodeList = listOf(
        PhoneCountryCode(
            code = "+7",
            icon = R.drawable.ru_flag
        ),
        PhoneCountryCode(
            code = "+1",
            icon = R.drawable.usa_flag
        ),
        PhoneCountryCode(
            code = "+44",
            icon = R.drawable.uk_flag
        ),
    )

    private val phoneNumberMutable = MutableStateFlow(PhoneNumber.default)
    private val phoneNumber = phoneNumberMutable.asStateFlow()

    private val pinCodeMutable = MutableStateFlow("")
    private val pinCode = pinCodeMutable.asStateFlow()

    private val _isPhoneNumberValid = combine(phoneNumberMutable){
        phoneNumberMutable.value.number.length == PHONE_NUMBER_REQUIRED_LENGTH
    }

    fun getPhoneNumberFlow() = phoneNumber

    fun getPinCodeFlow() = pinCode

    fun getIsPhoneNumberValidFlow() = _isPhoneNumberValid

    fun setVerificationPhoneNumber(phoneNumber: String) {
        phoneNumberMutable.update {
            it.copy(
                number = phoneNumber
            )
        }
    }

    fun setVerificationPhoneNumber(countryCode: PhoneCountryCode) {
        phoneNumberMutable.update {
            it.copy(
                countryCode = countryCode
            )
        }
    }

    fun setVerificationPinCode(pinCode: String){
        pinCodeMutable.update {
            pinCode
        }
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
                    phoneNumber = "${phoneNumberMutable.value.countryCode.code}${phoneNumberMutable.value.number}"
                )
            )
        }
    }
}