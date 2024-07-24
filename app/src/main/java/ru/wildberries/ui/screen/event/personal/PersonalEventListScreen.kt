package ru.wildberries.ui.screen.event.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.screen.event.list.HorizontalPagerEventList
import ru.wildberries.ui.screen.event.list.HorizontalPagerEventListClass
import ru.wildberries.ui.theme.WBTheme

@Composable
fun PersonalEventListScreen(
    viewModel: PersonalEventListViewModel = koinViewModel(),
) {

    val navController = LocalNavController.current
    val eventListFull by viewModel.getEventListFlow().collectAsState()
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
    val activeEventList = eventListFull.filter { event -> event.isActive }
    val inactiveEventList = eventListFull.filter { event -> !event.isActive }
    val horizontalPagerEventList = remember {
        listOf(
            HorizontalPagerEventListClass(
                eventList = activeEventList,
            ),
            HorizontalPagerEventListClass(
                eventList = inactiveEventList,
            ),
        )
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
        TopBar(
            title = stringResource(id = R.string.morescreen_my_events),
            navigationIcon = R.drawable.arrow_back,
            navigationIconOnClick = {navController.popBackStack()},
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 29.dp
                )
                .offset(x = (-24).dp)
        )
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
            with(horizontalPagerEventList[index]){
                HorizontalPagerEventList(
                    eventList = eventList,
                    navController = navController
                )
            }
        }
    }
}

data class TabItem(
    val title: String
)