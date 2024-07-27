package ru.wildberries.ui.screen.community.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.community.GetAllCommunityListUseCase
import ru.wildberries.ui.model.Community
import ru.wildberries.ui.model.toUiCommunity

internal class CommunityListViewModel(
    private val getAllCommunityListUSeCase: GetAllCommunityListUseCase
): ViewModel() {

    private var communityListMutable = MutableStateFlow(emptyList<Community>())
    private var communityList: StateFlow<List<Community>> = communityListMutable

    init {
        initCommunityList()
    }

    fun getCommunityListFlow() = communityList

    private fun initCommunityList() {
        viewModelScope.launch {
            communityListMutable.update {
                getAllCommunityListUSeCase().map { it.toUiCommunity() }
            }
        }
    }
}