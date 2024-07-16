package ru.wildberries.ui.screen.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wildberries.domain.model.Community
import ru.wildberries.domain.model.Event
import ru.wildberries.domain.Repository

class CommunityViewModel(
    private val repository: Repository
): ViewModel() {
    private var _communityList = MutableStateFlow(emptyList<Community>())
    val communityList = _communityList.asStateFlow()

    private var _eventList = MutableStateFlow(emptyList<Event>())
    val eventList = _eventList.asStateFlow()

    private fun getCommunityList() {
        viewModelScope.launch {
            repository.getCommunityList()
                .collect { communityList ->
                    _communityList.update { communityList }
                }
        }
    }

    private fun getEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .collect { eventList ->
                    _eventList.update { eventList }
                }
        }
    }

    init {
        getCommunityList()
        getEventList()
    }
}