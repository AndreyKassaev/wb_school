package ru.wildberries.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wildberries.domain.CommunityModel
import ru.wildberries.domain.EventModel
import ru.wildberries.domain.IMockRepository
import ru.wildberries.domain.ProfileModel
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg

class MainViewModel(
    val repository: IMockRepository
): ViewModel(){
    private var _selectedBottomAppBarItem = MutableSharedFlow<BottomAppBarItem>()
    val selectedBottomAppBarItem = _selectedBottomAppBarItem.asSharedFlow()

    private var _topAppBarArg by mutableStateOf(TopBarArg.default)
    val topAppBarArg: TopBarArg
        get() = _topAppBarArg

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

    fun setSelectedBottomAppBarItem(selectedBottomAppBarItem: BottomAppBarItem){
        viewModelScope.launch {
            _selectedBottomAppBarItem.emit(selectedBottomAppBarItem)
        }
    }

    private fun getProfileData() {
        _profileData = repository.getProfileData()
    }

    private fun getEventList(){
        viewModelScope.launch {
            repository.getEventList().collect{ eventList ->
                _eventList.update { eventList }
            }
        }
    }

    private fun getCommunityList(){
        viewModelScope.launch {
            repository.getCommunityList().collect{ communityList ->
                _communityList.update { communityList }
            }
        }
    }

    private fun getEventVisitorList(){
        viewModelScope.launch {
            repository.getEventVisitorList().collect{ visitorList ->
                _eventVisitorList.update { visitorList }
            }
        }
    }

    private fun isAppReady(){
        viewModelScope.launch {
            delay(3000L)
            _isAppReady = !(_eventList.firstOrNull().isNullOrEmpty())
        }
    }

    init {
        getProfileData()
        getEventList()
        getCommunityList()
        getEventVisitorList()
        isAppReady()
    }

    fun setTopAppBar(topBarArg: TopBarArg){
        _topAppBarArg = topAppBarArg
            .copy(
            title = topBarArg.title,
            navigationIcon = topBarArg.navigationIcon,
            navigationIconOnClick = topBarArg.navigationIconOnClick,
            actionIcon = topBarArg.actionIcon,
            actionIconOnclick = topBarArg.actionIconOnclick
        )
    }

}