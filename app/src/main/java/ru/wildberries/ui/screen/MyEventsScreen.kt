package ru.wildberries.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavController
import ru.wildberries.R
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.molecule.EventCard
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.ActivityContext

@Composable
fun MyEventsScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = getString(ActivityContext.context, R.string.morescreen_my_events),
                navigationIcon = R.drawable.arrow_back,
                navigationIconOnClick = { navController.popBackStack() },
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.More)
    }


    val myEventList by viewModel.eventList.collectAsState()
    val tabItemList = listOf(
        TabItem(title = stringResource(id = R.string.my_events_tabitem_planned)),
        TabItem(title = stringResource(id = R.string.my_events_tabitem_passed))
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
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
                            if (event.isActive){
                                item {
                                    Surface(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(event)
                                            }
                                    ) {
                                        EventCard(eventModel = event)
                                    }
                                    HorizontalDivider(
                                        color = WBTheme.colors.NeutralLine
                                    )
                                }
                            }
                        }
                    }
                }
                1 -> {
                    LazyColumn {
                        myEventList.forEach { event ->
                            if (!event.isActive){
                                item {
                                    Surface(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(event)
                                            }
                                    ) {
                                        EventCard(eventModel = event)
                                    }
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

data class TabItem(
    val title: String
)