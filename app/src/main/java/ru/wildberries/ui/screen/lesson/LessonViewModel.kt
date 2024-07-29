package ru.wildberries.ui.screen.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.community.GetAllCommunityListUseCase
import ru.wb.domain.usecase.event.GetAllEventListUseCase
import ru.wildberries.ui.model.Community
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.toUiCommunity
import ru.wildberries.ui.model.toUiEvent

internal class LessonViewModel(
    private val getAllEventListUseCase: GetAllEventListUseCase,
    private val getAllCommunityListUseCase: GetAllCommunityListUseCase,
): ViewModel() {
    private var eventListMutable = MutableStateFlow(emptyList<Event>())
    private val eventList: StateFlow<List<Event>> = eventListMutable

    private var communityListMutable = MutableStateFlow(emptyList<Community>())
    private val communityList: StateFlow<List<Community>> = communityListMutable

    init {
        initEventList()
        initCommunityList()
    }

    fun getEventListFlow() = eventList

    fun getCommunityListFlow() = communityList

    private fun initEventList() {
        viewModelScope.launch {
            getAllEventListUseCase().collectLatest { eventList ->
                eventListMutable.update {
                    eventList.map { it.toUiEvent() }
                }
            }
        }
    }

    private fun initCommunityList() {
        viewModelScope.launch {
            getAllCommunityListUseCase().collectLatest { communityList ->
                communityListMutable.update {
                    communityList.map { it.toUiCommunity() }
                }
            }
        }
    }

}