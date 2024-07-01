package ru.wildberries.ui.UIKit.organism

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.navigation.BottomNavigation
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.theme.WBTheme

@Composable
fun BottomBar(
    isEnabled: Boolean = true,
    viewModel: MainViewModel,
    bottomNavigation: BottomNavigation
) {
    val selectedBottomAppBarItem by viewModel.selectedBottomAppBarItem.collectAsState(initial = BottomAppBarItem.None)

    if (isEnabled){
        BottomAppBar(
            tonalElevation = 4.dp,
            containerColor = Color.Transparent,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomAppBarItem(
                    icon = R.drawable.coffee_togo,
                    title = R.string.appbar_item_events,
                    isActive =  selectedBottomAppBarItem == BottomAppBarItem.Events,
                    onClick = {
                        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.Events)
                        bottomNavigation.eventsNavigate()
                    }
                )
                BottomAppBarItem(
                    modifier = Modifier.size(width = 81.dp, height = 44.dp),
                    icon = R.drawable.group_alt,
                    title = R.string.appbar_item_communities,
                    isActive = selectedBottomAppBarItem == BottomAppBarItem.Communities,
                    onClick = {
                        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.Communities)
                        bottomNavigation.communityNavigate()
                    }
                )
                BottomAppBarItem(
                    icon = R.drawable.more_horizontal,
                    title = R.string.bottomappbar_item_more,
                    isActive = selectedBottomAppBarItem == BottomAppBarItem.More,
                    onClick = {
                        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.More)
                        bottomNavigation.moreNavigate()
                    }
                )
            }
        }
    }
}

enum class BottomAppBarItem {
    None,
    Events,
    Communities,
    More
}

@Composable
fun BottomAppBarItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: Int,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    val iconModifier = Modifier
        .padding(
            horizontal = 13.dp,
            vertical = 6.dp
        )
        .size(
            width = 32.dp,
            height = 32.dp
        )
    val iconButtonModifier = modifier
        .size(
            width = 58.dp,
            height = 44.dp
        )
        .then(modifier)

    IconButton(
        modifier = iconButtonModifier,
        onClick = onClick
    ) {
        if (isActive) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = stringResource(id = title),
                    style = WBTheme.typography.bodyText2,
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier
                        .size(4.dp),
                    painter = painterResource(R.drawable.bottomappbar_dot),
                    contentDescription = "",
                    tint = WBTheme.colors.NeutralActive
                )
            }
        }else{
            Icon(
                modifier = iconModifier,
                painter = painterResource(id = icon),
                contentDescription = "",
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun BottomBarPreview() {
    WBTheme {
        BottomBar(
            viewModel = MainViewModel(MockRepositoryImpl()),
            bottomNavigation = BottomNavigation(
                eventsNavigate = {},
                communityNavigate = {},
                moreNavigate = {}
            )
        )
    }
}
