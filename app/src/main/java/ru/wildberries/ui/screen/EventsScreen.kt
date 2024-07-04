package ru.wildberries.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.SearchBar
import ru.wildberries.ui.UIKit.molecule.EventCard
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme

@Composable
fun EventsScreen(
    viewModel: MainViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = R.string.appbar_item_events,
                navigationIcon = null,
                actionIcon = R.drawable.plus,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.Events)
    }


    val myEventList by viewModel.eventList.collectAsState()
    val tabItemList = listOf(
        TabItem(title = stringResource(id = R.string.events_tabitem_all)),
        TabItem(title = stringResource(id = R.string.events_tabitem_active))
    )
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItemList.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
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
        TabRow(
            selectedTabIndex = selectedTabIndex,
            divider = {},
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = WBTheme.colors.BrandColorDefault,
                    )
                }
            }
        ) {
            tabItemList.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = tabItem.title.uppercase())
                    },
                    selectedContentColor = WBTheme.colors.BrandColorDefault,
                    unselectedContentColor = WBTheme.colors.NeutralDisabled
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.Top
        ) { index ->
            when (index){
                0 -> {
                    LazyColumn {
                        myEventList.forEach { event ->
                            item {
                                EventCard(eventModel = event)
                                HorizontalDivider(
                                    color = WBTheme.colors.NeutralLine
                                )
                            }
                        }
                    }
                }
                1 -> {
                    LazyColumn {
                        myEventList.forEach { event ->
                            if (event.isActive){
                                item {
                                    EventCard(eventModel = event)
                                    HorizontalDivider(
                                        color = WBTheme.colors.NeutralLine
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EventsScreenPreview() {
    WBTheme{
        EventsScreen(
            viewModel = MainViewModel(MockRepositoryImpl()),
        )
    }
}