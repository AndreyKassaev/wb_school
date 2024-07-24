package ru.wildberries.ui.screen.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.molecule.Profile
import ru.wildberries.ui.UIKit.molecule.ProfileMode
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
fun MoreScreen(
    viewModel: MoreViewModel = koinViewModel(),
) {

    val navController = LocalNavController.current
    val profileData by viewModel.getProfileFlow().collectAsState()
    val itemList = listOf(
        MoreScreenItemClass(
            content = {
                Profile(
                    profileData = profileData,
                    profileState = ProfileState.None,
                    size = 100.dp,
                    profileMode = ProfileMode.Row,
                    onClick = {}
                )
            },
            action = { navController.navigate(Router.Profile.route) }
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.coffee_togo),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.morescreen_my_events),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = { navController.navigate(Router.PersonalEventList.route) }
        ),
        MoreScreenItemClass(
            content = {
                Text(
                    style = WBTheme.typography.bodyText1,
                    text = "Lesson 1"
                )
            },
            action = { navController.navigate(Router.FirstLesson.route) }
        ),
        MoreScreenItemClass(
            content = {
                Text(
                    style = WBTheme.typography.bodyText1,
                    text = "Lesson 2"
                )
            },
            action = { navController.navigate(Router.SecondLesson.route) }
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.theme_light),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.theme),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.notification),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.theme_light),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.theme),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.security),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.security),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.folder),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.memory_and_recources),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.help),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.help),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        ),
        MoreScreenItemClass(
            content = {
                Icon(
                    painter = painterResource(R.drawable.envelope),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.invite_friend),
                    style = WBTheme.typography.bodyText1
                )
            },
            action = {}
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        TopBar(
            title = stringResource(R.string.appbar_item_more),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        itemList.forEach {
            MoreScreenItem(
                content = it.content,
                action = it.action
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun MoreScreenPreview() {
    WBTheme {
        MoreScreen()
    }
}

@Composable
fun MoreScreenItem(
    content: @Composable () -> Unit,
    action: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                action()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                action()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = ""
            )
        }
    }
}

data class MoreScreenItemClass(
    val content: @Composable () -> Unit,
    val action: () -> Unit
)