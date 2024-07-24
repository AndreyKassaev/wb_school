package ru.wildberries.ui.screen.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.community.GetAllCommunityListUseCase
import ru.wb.domain.usecase.event.GetAllEventListUseCase
import ru.wb.domain.usecase.event.GetEventVisitorListUseCase
import ru.wildberries.ui.model.Community
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.EventVisitor
import ru.wildberries.ui.model.toUiCommunity
import ru.wildberries.ui.model.toUiEvent
import ru.wildberries.ui.model.toUiEventVisitor

class LessonViewModel(
    private val getAllEventListUseCase: GetAllEventListUseCase,
    private val getAllCommunityListUSeCase: GetAllCommunityListUseCase,
    private val getEventVisitorList: GetEventVisitorListUseCase
): ViewModel() {
    private var eventListMutable = MutableStateFlow(emptyList<Event>())
    private val eventList: StateFlow<List<Event>> = eventListMutable

    private var communityListMutable = MutableStateFlow(emptyList<Community>())
    private val communityList: StateFlow<List<Community>> = communityListMutable

    private var visitorListMutable = MutableStateFlow(emptyList<EventVisitor>())
    private val visitorList: StateFlow<List<EventVisitor>> = visitorListMutable

    init {
        initEventList()
        initCommunityList()
        initVisitorList()
    }

    internal fun getEventListFlow() = eventList

    internal fun getCommunityListFlow() = communityList

    internal fun getEventVisitorListFlow() = visitorList

    private fun initEventList() {
        viewModelScope.launch {
            eventListMutable.update {
                getAllEventListUseCase().map { it.toUiEvent() }
            }
        }
    }

    private fun initCommunityList() {
        viewModelScope.launch {
            communityListMutable.update {
                getAllCommunityListUSeCase().map { it.toUiCommunity() }
            }
        }
    }

    private fun initVisitorList(){
        viewModelScope.launch {
            visitorListMutable.update {
                getEventVisitorList(eventId = "").map { it.toUiEventVisitor() }
            }
        }
    }

}