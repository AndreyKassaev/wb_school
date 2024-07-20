package ru.wildberries.ui.UIKit.organism

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.wildberries.R
import ru.wildberries.navigation.Route
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.classToRoute

data class BottomNavigationItem(
    val icon: Int,
    val title: Int,
    val isSelected: Boolean,
    val navigateTo: () -> Unit,
    val route: String
)


@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackEntry?.destination?.route
        ?: Route.EventList.classToRoute()

    val itemList = listOf(
        BottomNavigationItem(
            icon = R.drawable.coffee_togo,
            title = R.string.appbar_item_events,
            isSelected = false,
            navigateTo = {
                navController.navigate(Route.EventList)
            },
            route = Route.EventList.classToRoute()
        ),
        BottomNavigationItem(
            icon = R.drawable.group_alt,
            title = R.string.appbar_item_communities,
            isSelected = false,
            navigateTo = {
                navController.navigate(Route.CommunityList)
            },
            route = Route.CommunityList.classToRoute()
        ),
        BottomNavigationItem(
            icon = R.drawable.more_horizontal,
            title = R.string.appbar_item_more,
            isSelected = false,
            navigateTo = {
                navController.navigate(Route.More)
            },
            route = Route.More.classToRoute()
        ),
    )
    when (currentDestination) {
        Route.Splash.classToRoute(),
        Route.VerificationPinCode.classToRoute(),
        Route.VerificationPhoneNumber.classToRoute() -> {
        }

        else -> Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .drawWithContent {
                    drawContent()
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(
                            0f,
                            0f
                        ),
                        end = Offset(
                            size.width,
                            0f
                        ),
                        strokeWidth = 2f
                    )
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemList.forEach { item ->
                BottomBarItem(
                    item = item,
                    currentDestination = currentDestination
                )
            }

        }

    }
}

@Composable
fun BottomBarItem(
    item: BottomNavigationItem,
    currentDestination: String
) {
    val selected = currentDestination == item.route

    IconButton(
        modifier = Modifier
            .size(
                width = 81.dp,
                height = 44.dp
            ),
        onClick = {
            if (!selected) {
                item.navigateTo()
            }
        }
    ) {
        if (selected) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = stringResource(id = item.title),
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
        } else {
            Icon(
                modifier = Modifier
                    .padding(
                        horizontal = 13.dp,
                        vertical = 6.dp
                    )
                    .size(
                        width = 32.dp,
                        height = 32.dp
                    ),
                painter = painterResource(id = item.icon),
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
        BottomBar(rememberNavController())
    }
}
