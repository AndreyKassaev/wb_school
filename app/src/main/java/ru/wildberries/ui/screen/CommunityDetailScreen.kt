package ru.wildberries.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.wildberries.R
import ru.wildberries.domain.CommunityModel
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.molecule.EventCard
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme

@Composable
fun CommunityDetailScreen(
    community: CommunityModel,
    viewModel: MainViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = community.title,
                navigationIcon = R.drawable.arrow_back,
                navigationIconOnClick = { navController.popBackStack() },
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.Communities)
    }

    val communityEventList = viewModel.eventList.collectAsState().value.filter { event ->
        event.location == community.title
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 30.dp),
            text = community.description,
            color = WBTheme.colors.NeutralWeak,
            style = WBTheme.typography.metadata1
        )
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.community_events),
            style = WBTheme.typography.bodyText1,
            color = WBTheme.colors.NeutralWeak
        )
        LazyColumn {
            communityEventList.forEach { event ->
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