package ru.wildberries.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.Repository
import ru.wb.domain.model.Profile
import ru.wildberries.R

class AuthViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _phoneNumber = MutableStateFlow(PhoneNumber.default)
    val phoneNumber = _phoneNumber.asStateFlow()

    private var _pinCode = MutableStateFlow("")
    val pinCode = _pinCode.asStateFlow()

    fun setVerificationPhoneNumber(phoneNumber: String) {
        _phoneNumber.update {
            it.copy(
                number = phoneNumber
            )
        }
    }

    fun setVerificationPhoneNumber(countryCode: PhoneCountryCode) {
        _phoneNumber.update {
            it.copy(
                countryCode = countryCode
            )
        }
    }

    fun setVerificationPinCode(pinCode: String){
        _pinCode.update {
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
                    phoneNumber = "${_phoneNumber.value.countryCode.code}${_phoneNumber.value.number}"
                )
            )
        }
    }
}

data class PhoneNumber(
    val countryCode: PhoneCountryCode,
    val number: String
){
    companion object {
        val default = PhoneNumber(
            countryCode = PhoneCountryCode.default,
            number = ""
        )
    }
}

data class PhoneCountryCode(
    val code: String,
    val icon: Int
){
    companion object {
        val default = PhoneCountryCode(
            code = "+7",
            icon = R.drawable.ru_flag
        )
    }
}

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
