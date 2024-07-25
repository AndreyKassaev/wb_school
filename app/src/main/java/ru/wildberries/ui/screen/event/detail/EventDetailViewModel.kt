package ru.wildberries.ui.screen.event.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.event.AcceptEventInvitationUseCase
import ru.wb.domain.usecase.event.GetEventByIdUseCase
import ru.wb.domain.usecase.event.GetEventVisitorListUseCase
import ru.wb.domain.usecase.event.RevokeEventInvitationUseCase
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.EventVisitor
import ru.wildberries.ui.model.toUiEvent
import ru.wildberries.ui.model.toUiEventVisitor

internal class EventDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val getEventVisitorListUseCase: GetEventVisitorListUseCase,
    private val acceptEventInvitationUseCase: AcceptEventInvitationUseCase,
    private val revokeEventInvitationUseCase: RevokeEventInvitationUseCase
): ViewModel() {

    private val currentEventMutable = MutableStateFlow(Event.default)
    private val currentEvent: StateFlow<Event> = currentEventMutable

    private val eventVisitorListMutable = MutableStateFlow(emptyList<EventVisitor>())
    private val eventVisitorList: StateFlow<List<EventVisitor>> = eventVisitorListMutable

    init {
        initCurrentEvent()
        initEventVisitorList()
    }

    private fun initCurrentEvent(){
        savedStateHandle.get<String>("event_id")?.let { eventId ->
            viewModelScope.launch {
                currentEventMutable.update {
                    getEventByIdUseCase(eventId = eventId).toUiEvent()
                }
            }
        }
    }

    private fun initEventVisitorList() {
        savedStateHandle.get<String>("event_id")?.let { eventId ->
            viewModelScope.launch {
                eventVisitorListMutable.update {
                    getEventVisitorListUseCase(eventId = eventId).map { it.toUiEventVisitor() }
                }
            }
        }
    }

    fun getCurrentEventFlow() = currentEvent

    fun getEventVisitorListFlow() = eventVisitorList

    fun acceptEventInvitation() {
        viewModelScope.launch {
            eventVisitorListMutable.update {
                acceptEventInvitationUseCase(currentEvent.value.id).map { it.toUiEventVisitor() }
            }
        }
    }

    fun revokeEventInvitation() {
        viewModelScope.launch {
            eventVisitorListMutable.update {
                revokeEventInvitationUseCase(currentEvent.value.id).map { it.toUiEventVisitor() }
            }
        }
    }
}