package ru.wildberries.ui.screen.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.Repository
import ru.wb.domain.model.Community
import ru.wb.domain.model.Event
import ru.wb.domain.model.Profile

class LessonViewModel(
    private val repository: Repository
): ViewModel() {
    private var eventListMutable = MutableStateFlow(emptyList<Event>())
    private val eventList = eventListMutable.asStateFlow()

    private var communityListMutable = MutableStateFlow(emptyList<Community>())
    private val communityList = communityListMutable.asStateFlow()

    private var profileMutable = MutableStateFlow(Profile.default)
    private val profile = profileMutable.asStateFlow()

    fun getEventListFlow() = eventList

    fun getCommunityListFlow()= communityList

    fun getProfileFlow() = profile

    private fun initEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .collect { eventList ->
                    eventListMutable.update { eventList }
                }
        }
    }

    private fun initCommunityList() {
        viewModelScope.launch {
            repository.getCommunityList()
                .collect { communityList ->
                    communityListMutable.update { communityList }
                }
        }
    }

    init {
        initEventList()
        initCommunityList()
    }
}