package ru.wildberries.ui.screen.community.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.atom.SearchBar
import ru.wildberries.ui.UIKit.molecule.CommunityCard
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun CommunityListScreen(
    viewModel: CommunityListViewModel = koinViewModel(),
) {

    val navController = LocalNavController.current
    val communityList by viewModel.getCommunityListFlow().collectAsState()
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
        TopBar(
            title = stringResource(id = R.string.appbar_item_communities),
            modifier = Modifier.padding(top=16.dp, bottom = 13.dp)
        )
        SearchBar()
        LazyColumn() {
            communityList.forEachIndexed { index, community ->
                item {
                    Surface(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Router.Community.withArg(communityId = community.id))
                            }
                    ) {
                        CommunityCard(community = community)
                    }
                    HorizontalDivider(
                        color = WBTheme.colors.NeutralLine
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunitiesScreenPrev() {
    WBTheme {
        CommunityListScreen()
    }
}