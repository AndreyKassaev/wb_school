package ru.wildberries.ui.screen.event.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.event.GetAllEventListUseCase
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.toUiEvent

class EventListViewModel(
    private val getAllEventListUseCase: GetAllEventListUseCase
): ViewModel() {

    private val eventListMutable = MutableStateFlow(emptyList<Event>())
    private val eventList: StateFlow<List<Event>> = eventListMutable


    init {
        initEventList()
    }

    internal fun getEventListFlow() = eventList

    private fun initEventList() {
        viewModelScope.launch {
            eventListMutable.update {
               getAllEventListUseCase().map { it.toUiEvent() }
            }
        }
    }

}