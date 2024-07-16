package ru.wildberries.ui.screen.lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wildberries.domain.model.Community
import ru.wildberries.domain.model.Event
import ru.wildberries.domain.model.Profile
import ru.wildberries.domain.Repository

class LessonViewModel(
    private val repository: Repository
): ViewModel() {
    private var _eventList = MutableStateFlow(emptyList<Event>())
    val eventList = _eventList.asStateFlow()

    private var _communityList = MutableStateFlow(emptyList<Community>())
    val communityList = _communityList.asStateFlow()

    private var _profile by mutableStateOf(Profile.default)
    val profile: Profile
        get() = _profile

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

    init {
        getEventList()
        getCommunityList()
    }
}