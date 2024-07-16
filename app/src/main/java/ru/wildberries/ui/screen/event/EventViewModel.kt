package ru.wildberries.ui.screen.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wildberries.domain.model.Event
import ru.wildberries.domain.Repository
import ru.wildberries.domain.model.Profile

class EventViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _eventList = MutableStateFlow(emptyList<Event>())
    val eventList = _eventList.asStateFlow()

    private var _eventVisitorList = MutableStateFlow(emptyList<Profile>())
    val eventVisitorList = _eventVisitorList.asStateFlow()

    private fun getEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .collect { eventList ->
                    _eventList.update { eventList }
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

    fun acceptInvitation() {
        viewModelScope.launch {
            repository.setEventVisitorList(getProfileData())
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

    private fun getProfileData() =
        repository.getProfileData()

    init {
        getEventList()
        getEventVisitorList()
    }
}