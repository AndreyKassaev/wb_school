package ru.wildberries.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init
import ru.wildberries.domain.CommunityModel
import ru.wildberries.domain.EventModel
import ru.wildberries.domain.IRepository
import ru.wildberries.domain.ProfileModel
import ru.wildberries.ui.UIKit.molecule.PhoneCountryCode

class MainViewModel(
    val repository: IRepository
) : ViewModel() {

    private var _profileData by mutableStateOf(ProfileModel.default)
    val profileData: ProfileModel
        get() = _profileData

    private var _isAppReady by mutableStateOf(false)
    val isAppReady: Boolean
        get() = _isAppReady

    private var _eventList = MutableStateFlow(emptyList<EventModel>())
    val eventList = _eventList.asStateFlow()

    private var _communityList = MutableStateFlow(emptyList<CommunityModel>())
    val communityList = _communityList.asStateFlow()

    private var _eventVisitorList = MutableStateFlow(emptyList<ProfileModel>())
    val eventVisitorList = _eventVisitorList.asStateFlow()

    private var _verificationPhoneNumber by mutableStateOf("")
    val verificationPhoneNumber: String
        get() = _verificationPhoneNumber

    private var _verificationPhoneNumberCountryCode by mutableStateOf("+7")
    val verificationPhoneNumberCountryCode: String
        get() = _verificationPhoneNumberCountryCode

    private var _verificationPinCode by mutableStateOf("")
    val verificationPinCode: String
        get() = _verificationPinCode

    private fun getProfileData() {
        _profileData = repository.getProfileData()
    }

    fun setProfileData(firstName: String, lastName: String = ""){
        _profileData = _profileData.copy(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = "$verificationPhoneNumberCountryCode$verificationPhoneNumber"
        )
    }

    private fun getEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .collect { eventList ->
                    _eventList.update { eventList }
                }
        }
    }

    private fun getCommunityList() {
        viewModelScope.launch {
            repository.getCommunityList()
                .collect { communityList ->
                    _communityList.update { communityList }
                }
        }
    }

    private fun getEventVisitorList() {
        viewModelScope.launch {
            repository.getEventVisitorList()
                .collect { visitorList ->
                    _eventVisitorList.update { visitorList }
                }
        }
    }

    private fun isAppReady() {
        viewModelScope.launch {
            delay(3000L)
            _isAppReady = !(_eventList.firstOrNull()
                .isNullOrEmpty())
        }
    }

    fun goToEvent(visitor: ProfileModel) {
        viewModelScope.launch {
            repository.setEventVisitorList(visitor)
                .collect { updatedList ->
                    _eventVisitorList.update { updatedList }
                }
        }
    }

    fun revokeInvitation() {
        viewModelScope.launch {
            repository.setEventVisitorList()
                .collect { updatedList ->
                    _eventVisitorList.update { updatedList }
                }
        }
    }

    fun setVerificationPhoneNumber(phoneNumber: String) {
        _verificationPhoneNumber = phoneNumber
    }

    fun setVerificationPhoneNumberCountryCode(countryCode: String) {
        _verificationPhoneNumberCountryCode = countryCode
    }

    fun setVerificationPinCode(pinCode: String){
        _verificationPinCode = pinCode
    }

    init {
        getProfileData()
        getEventList()
        getCommunityList()
        getEventVisitorList()
        isAppReady()
    }
}