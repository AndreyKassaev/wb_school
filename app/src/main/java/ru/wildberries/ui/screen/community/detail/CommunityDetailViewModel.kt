package ru.wildberries.ui.screen.community.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.community.GetCommunityByIdUseCase
import ru.wb.domain.usecase.community.GetCommunityEventListUSeCase
import ru.wildberries.ui.model.Community
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.model.toUiCommunity
import ru.wildberries.ui.model.toUiEvent

class CommunityDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCommunityByIdUseCase: GetCommunityByIdUseCase,
    private val getCommunityEventListUSeCase: GetCommunityEventListUSeCase
): ViewModel() {

    private val currentCommunityMutable = MutableStateFlow(Community.default)
    private val currentCommunity: StateFlow<Community> = currentCommunityMutable

    private var eventListMutable = MutableStateFlow(emptyList<Event>())
    private var eventList: StateFlow<List<Event>> = eventListMutable

    init {
        initCurrentCommunity()
        initEventList()
    }

    internal fun getCurrentCommunityFlow() = currentCommunity

    internal fun getEventListFlow() = eventList

    private fun initCurrentCommunity(){
        savedStateHandle.get<String>("community_id")?.let { communityId ->
            viewModelScope.launch {
                currentCommunityMutable.update {
                    getCommunityByIdUseCase(communityId = communityId).toUiCommunity()
                }
            }
        }
    }

    private fun initEventList(){
        savedStateHandle.get<String>("community_id")?.let{ communityId ->
            viewModelScope.launch {
                eventListMutable.update {
                    getCommunityEventListUSeCase(communityId = communityId).map { it.toUiEvent() }
                }
            }
        }
    }

}