package ru.wildberries.ui.screen.community.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.community.GetAllCommunityListUSeCase
import ru.wildberries.ui.model.Community
import ru.wildberries.ui.model.toUiCommunity

class CommunityListViewModel(
    private val getAllCommunityListUSeCase: GetAllCommunityListUSeCase
): ViewModel() {

    private var communityListMutable = MutableStateFlow(emptyList<Community>())
    private var communityList: StateFlow<List<Community>> = communityListMutable

    init {
        initCommunityList()
    }

    internal fun getCommunityListFlow() = communityList

    private fun initCommunityList() {
        viewModelScope.launch {
            communityListMutable.update {
                getAllCommunityListUSeCase().map { it.toUiCommunity() }
            }
        }
    }
}