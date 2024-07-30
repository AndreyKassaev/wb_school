package ru.wildberries.ui.screen.community.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.community.GetCommunityByIdUseCase
import ru.wildberries.ui.model.Community
import ru.wildberries.ui.model.toUiCommunity

internal class CommunityDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCommunityByIdUseCase: GetCommunityByIdUseCase,
): ViewModel() {

    private val currentCommunityMutable = MutableStateFlow(Community.default)
    private val currentCommunity: StateFlow<Community> = currentCommunityMutable

    init {
        initCurrentCommunity()
    }

    fun getCurrentCommunityFlow() = currentCommunity

    private fun initCurrentCommunity(){
        savedStateHandle.get<String>("community_id")?.let { communityId ->
            viewModelScope.launch {
                getCommunityByIdUseCase(communityId = communityId).collectLatest { community ->
                    currentCommunityMutable.update {
                        community.toUiCommunity()
                    }
                }
            }
        }
    }

}