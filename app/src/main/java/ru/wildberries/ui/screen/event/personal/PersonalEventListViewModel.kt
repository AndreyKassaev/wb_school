package ru.wildberries.ui.screen.event.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.event.GetPersonalEventListUseCase
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.toUiEvent

internal class PersonalEventListViewModel(
    private val getPersonalEventListUseCase: GetPersonalEventListUseCase
): ViewModel() {

    private val eventListMutable = MutableStateFlow(emptyList<Event>())
    private val eventList: StateFlow<List<Event>> = eventListMutable

    init {
        initEventList()
    }

    fun getEventListFlow() = eventList

    private fun initEventList() {
        viewModelScope.launch {
            eventListMutable.update {
                getPersonalEventListUseCase().map { it.toUiEvent() }
            }
        }
    }

}