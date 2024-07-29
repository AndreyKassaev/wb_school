package ru.wildberries.ui.screen.community.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.molecule.EventCard
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun CommunityDetailScreen(
    viewModel: CommunityDetailViewModel = koinViewModel(),
) {

    val navController = LocalNavController.current
    val community by viewModel.getCurrentCommunityFlow().collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TopBar(
            title = community.title,
            navigationIcon = R.drawable.arrow_back,
            navigationIconOnClick = {navController.popBackStack()},
            modifier = Modifier.padding(start = 12.dp, top = 16.dp, bottom = 21.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 30.dp),
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
                community.eventList.forEach { event ->
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
    }
}