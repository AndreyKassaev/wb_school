package ru.wildberries.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.SearchBar
import ru.wildberries.ui.UIKit.molecule.CommunityCard
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme

@Composable
fun CommunitiesScreen(
    viewModel: MainViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = R.string.appbar_item_communities,
                navigationIcon = null,
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.Communities)
    }

    val communityList by viewModel.communityList.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusManager.clearFocus()
            },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchBar()
        LazyColumn() {
            communityList.forEach { community ->
                item {
                    CommunityCard(communityModel = community)
                    HorizontalDivider(
                        color = WBTheme.colors.NeutralLine
                    )
                }
            }
        }
    }
}