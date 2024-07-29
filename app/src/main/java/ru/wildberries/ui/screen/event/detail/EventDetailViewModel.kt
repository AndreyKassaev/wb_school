package ru.wildberries.ui.screen.event.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.event.AcceptEventInvitationUseCase
import ru.wb.domain.usecase.event.GetEventByIdUseCase
import ru.wb.domain.usecase.event.RevokeEventInvitationUseCase
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.toUiEvent

internal class EventDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val acceptEventInvitationUseCase: AcceptEventInvitationUseCase,
    private val revokeEventInvitationUseCase: RevokeEventInvitationUseCase
): ViewModel() {

    private val currentEventMutable = MutableStateFlow(Event.default)
    private val currentEvent: StateFlow<Event> = currentEventMutable

    private val isInvitationAcceptedMutable = MutableStateFlow(false)
    private val isInvitationAccepted: StateFlow<Boolean> = isInvitationAcceptedMutable

    init {
        initCurrentEvent()
    }

    fun getIsInvitationAcceptedFlow() = isInvitationAccepted

    fun getCurrentEventFlow() = currentEvent

    fun acceptEventInvitation() {
        savedStateHandle.get<String>("event_id")
            ?.let { eventId ->
                viewModelScope.launch {
                    acceptEventInvitationUseCase(eventId).collectLatest { event ->
                        currentEventMutable.update {
                            event.toUiEvent()
                        }
                    }
                    isInvitationAcceptedMutable.update {
                        true
                    }
            }
        }
    }

    fun revokeEventInvitation() {
        savedStateHandle.get<String>("event_id")
            ?.let { eventId ->
                viewModelScope.launch {
                    revokeEventInvitationUseCase(eventId = eventId).collectLatest { event ->
                        currentEventMutable.update {
                            event.toUiEvent()
                        }
                        isInvitationAcceptedMutable.update {
                            false
                        }
                    }
                }
            }
    }

    private fun initCurrentEvent() {
        savedStateHandle.get<String>("event_id")
            ?.let { eventId ->
                viewModelScope.launch {
                    getEventByIdUseCase(eventId = eventId).collectLatest { event ->
                        currentEventMutable.update {
                            event.toUiEvent()
                        }
                    }
                }
            }
    }

}
