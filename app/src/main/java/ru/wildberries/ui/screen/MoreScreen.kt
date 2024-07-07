package ru.wildberries.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import ru.wildberries.R
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.molecule.Profile
import ru.wildberries.ui.UIKit.molecule.ProfileMode
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.ActivityContext

@Composable
fun MoreScreen(
    viewModel: MainViewModel,
    navigateToProfile: () -> Unit,
    navigateToFirstLesson: () -> Unit,
    navigateToSecondLesson: () -> Unit,
    navigateToMyEvents: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = getString(ActivityContext.context, R.string.bottomappbar_item_more),
                navigationIcon = null,
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.More)
    }

    val profileData = viewModel.profileData

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        MoreScreenItem(
            content = {
                Profile(
                    profileData = profileData,
                    profileState = ProfileState.None,
                    size = 100.dp,
                    profileMode = ProfileMode.Row,
                    onClick = {}
                )
            },
            action = navigateToProfile
        )
        Spacer(modifier = Modifier.height(8.dp))
        MoreScreenItem(
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
            action = {
                navigateToMyEvents()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MoreScreenItem(
            content = {
                Text(
                    style = WBTheme.typography.bodyText1,
                    text = "Lesson 1"
                )
            },
            action = { navigateToFirstLesson() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MoreScreenItem(
            content = {
                Text(
                    style = WBTheme.typography.bodyText1,
                    text = "Lesson 2"
                )
            },
            action = { navigateToSecondLesson() }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun MoreScreenPreview() {
    WBTheme {
        MoreScreen(
            viewModel = MainViewModel(MockRepositoryImpl()),
            navigateToProfile = {},
            navigateToFirstLesson = {},
            navigateToSecondLesson = {},
            navigateToMyEvents = {}
        )
    }
}

@Composable
fun MoreScreenItem(
    content: @Composable () -> Unit,
    action: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
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