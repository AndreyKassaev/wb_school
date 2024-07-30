package ru.wildberries.ui.screen.auth.phone

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import ru.wb.domain.usecase.auth.RequestPinCodeUseCase
import ru.wildberries.R
import ru.wildberries.ui.model.CountryCode
import ru.wildberries.ui.model.PhoneNumber

const val PHONE_NUMBER_REQUIRED_LENGTH = 10

internal class PhoneNumberViewModel(
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

    fun getPhoneNumberFlow() = phoneNumber

    fun getIsPhoneNumberValidFlow() =
        flowOf(
            phoneNumber.value.number.length == PHONE_NUMBER_REQUIRED_LENGTH
        )

    fun setPhoneNumber(phoneNumber: String) {
        phoneNumberMutable.update {
            it.copy(
                number = phoneNumber
            )
        }
    }

    fun setPhoneCountryCode(countryCode: CountryCode) {
        phoneNumberMutable.update {
            it.copy(
                countryCode = countryCode
            )
        }
    }

    fun requestPinCode(){
        //TODO("local push")
        requestPinCodeUseCase()
    }
}