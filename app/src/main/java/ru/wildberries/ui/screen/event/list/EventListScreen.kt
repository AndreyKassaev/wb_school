package ru.wildberries.ui.screen.event.list

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.atom.SearchBar
import ru.wildberries.ui.UIKit.molecule.EventCard
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.model.Event
import ru.wildberries.ui.screen.event.personal.TabItem
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun EventListScreen(
    viewModel: EventListViewModel = koinViewModel(),
) {

    val navController = LocalNavController.current
    val eventListFull by viewModel.getEventListFlow().collectAsState()
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
    val activeEventList = eventListFull.filter { event -> event.isActive }
    val horizontalPagerEventList = remember {
        listOf(
            HorizontalPagerEventListClass(
                eventList = eventListFull,
            ),
            HorizontalPagerEventListClass(
                eventList = activeEventList,
            ),
        )
    }
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
        TopBar(
            title = stringResource(R.string.appbar_item_events),
            navigationIcon = null,
            actionIcon = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus ),
                        contentDescription = null,
                        tint = WBTheme.colors.NeutralActive
                    )
                }
            },
            modifier = Modifier.padding(
                bottom = 13.dp,
                top = 16.dp
            )
        )
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
            with(horizontalPagerEventList[index]){
                HorizontalPagerEventList(
                    eventList = eventList,
                    navController = navController
                )
            }
        }
    }
}

internal data class HorizontalPagerEventListClass(
    val eventList: List<Event>
)

@Composable
internal fun HorizontalPagerEventList(
    eventList: List<Event>,
    navController: NavController
) {
    LazyColumn {
        eventList.forEach { event ->
            item {
                Surface(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Router.Event.withArg(eventId = event.id))
                        }
                ) {
                    EventCard(event = event)
                }
                HorizontalDivider(
                    color = WBTheme.colors.NeutralLine
                )
            }
        }
    }
}