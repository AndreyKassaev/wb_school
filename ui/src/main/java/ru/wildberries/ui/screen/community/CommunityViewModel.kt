package ru.wildberries.ui.screen.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.Repository
import ru.wb.domain.model.Community
import ru.wb.domain.model.Event

class CommunityViewModel(
    private val repository: Repository
): ViewModel() {
    private var communityListMutable = MutableStateFlow(emptyList<Community>())
    private var communityList = communityListMutable.asStateFlow()

    private var eventListMutable = MutableStateFlow(emptyList<Event>())
    private var eventList = eventListMutable.asStateFlow()

    fun getCommunityListFlow() = communityList

    fun getEventListFlow() = eventList

    private fun initCommunityList() {
        viewModelScope.launch {
            repository.getCommunityList()
                .collect { communityList ->
                    communityListMutable.update { communityList }
                }
        }
    }

    private fun initEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .collect { eventList ->
                    eventListMutable.update { eventList }
                }
        }
    }

    init {
        initCommunityList()
        initEventList()
    }
}