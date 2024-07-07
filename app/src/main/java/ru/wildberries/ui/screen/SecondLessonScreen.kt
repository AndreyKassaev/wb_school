package ru.wildberries.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import ru.wildberries.R
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.molecule.CommunityCard
import ru.wildberries.ui.UIKit.molecule.EventCardList
import ru.wildberries.ui.UIKit.molecule.EventVisitorAvatarList
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.util.ActivityContext

@Composable
fun SecondLessonScreen(
    viewModel: MainViewModel,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = getString(ActivityContext.context, R.string.homework_two),
                navigationIcon = R.drawable.arrow_back,
                navigationIconOnClick = navigateBack,
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.More)
    }

    val eventList by viewModel.eventList.collectAsState()
    val communityList by viewModel.communityList.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            EventCardList(eventList)
        }

        item {
            EventVisitorAvatarList(
                eventVisitorList = (0..13).map { viewModel.profileData }
            )
        }

        item {
            CommunityCard(communityList.first())
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProfileImage(
                    profileState = ProfileState.Add,
                    imageUrl = null,
                    size = 100.dp,
                    onClick = {}

                )
                ProfileImage(
                    profileState = ProfileState.Edit,
                    imageUrl = null,
                    size = 100.dp,
                    onClick = {}
                )
            }
        }
    }
}
