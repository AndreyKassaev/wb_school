package ru.wildberries.ui.screen.auth.phone

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.auth.RequestPinCodeUseCase
import ru.wildberries.R
import ru.wildberries.ui.model.CountryCode
import ru.wildberries.ui.model.PhoneNumber

const val PHONE_NUMBER_REQUIRED_LENGTH = 10

class PhoneNumberViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val requestPinCodeUseCase: RequestPinCodeUseCase
): ViewModel() {

    internal val countryCodeLists = listOf(
        CountryCode(
            code = "+7",
            icon = R.drawable.ru_flag
        ),
        CountryCode(
            code = "+1",
            icon = R.drawable.usa_flag
        ),
        CountryCode(
            code = "+44",
            icon = R.drawable.uk_flag
        ),
    )

    private val phoneNumberMutable = MutableStateFlow(PhoneNumber.default)
    private val phoneNumber: StateFlow<PhoneNumber> = phoneNumberMutable

    private val _isPhoneNumberValid = combine(phoneNumberMutable){
        phoneNumberMutable.value.number.length == PHONE_NUMBER_REQUIRED_LENGTH
    }

    internal fun getPhoneNumberFlow() = phoneNumber

    fun getIsPhoneNumberValidFlow() = _isPhoneNumberValid

    fun setPhoneNumber(phoneNumber: String) {
        phoneNumberMutable.update {
            it.copy(
                number = phoneNumber
            )
        }
    }

    internal fun setPhoneCountryCode(countryCode: CountryCode) {
        phoneNumberMutable.update {
            it.copy(
                countryCode = countryCode
            )
        }
    }

    fun requestPinCode(){
        viewModelScope.launch {
            //local push
            //requestPinCodeUseCase()
        }
    }
}