package ru.wildberries.ui.screen.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.Repository
import ru.wb.domain.model.Event
import ru.wb.domain.model.Profile

class EventViewModel(
    private val repository: Repository
) : ViewModel() {
    private var eventListMutable = MutableStateFlow(emptyList<Event>())
    private val eventList = eventListMutable.asStateFlow()

    private var eventVisitorListMutable = MutableStateFlow(emptyList<Profile>())
    private val eventVisitorList = eventVisitorListMutable.asStateFlow()

    fun getEventListFlow() = eventList

    fun getEventVisitorListFlow() = eventVisitorList

    private fun initEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .collect { eventList ->
                    eventListMutable.update { eventList }
                }
        }
    }

    private fun initEventVisitorList() {
        viewModelScope.launch {
            repository.getEventVisitorList()
                .collect { visitorList ->
                    eventVisitorListMutable.update { visitorList }
                }
        }
    }

    fun acceptInvitation() {
        viewModelScope.launch {
            repository.setEventVisitorList(getProfileData())
                .collect { updatedList ->
                    eventVisitorListMutable.update { updatedList }
                }
        }
    }

    fun revokeInvitation() {
        viewModelScope.launch {
            repository.setEventVisitorList()
                .collect { updatedList ->
                    eventVisitorListMutable.update { updatedList }
                }
        }
    }

    private fun getProfileData() =
        repository.getProfileData()

    init {
        initEventList()
        initEventVisitorList()
    }
}