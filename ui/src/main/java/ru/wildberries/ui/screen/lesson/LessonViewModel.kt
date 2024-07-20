package ru.wildberries.ui.screen.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.Repository
import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.model.Profile

class LessonViewModel(
    private val repository: Repository
): ViewModel() {
    private var _eventList = MutableStateFlow(emptyList<Event>())
    val eventList = _eventList.asStateFlow()

    private var _communityList = MutableStateFlow(emptyList<Community>())
    val communityList = _communityList.asStateFlow()

    private var _profile = MutableStateFlow(Profile.default)
    val profile = _profile.asStateFlow()

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