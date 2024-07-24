package ru.wildberries.ui.screen.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.molecule.CommunityCard
import ru.wildberries.ui.UIKit.molecule.EventCardList
import ru.wildberries.ui.UIKit.molecule.EventVisitorAvatarList

@Composable
fun SecondLessonScreen(
    viewModel: LessonViewModel = koinViewModel(),
) {
    val eventList by viewModel.getEventListFlow().collectAsState()
    val communityList by viewModel.getCommunityListFlow().collectAsState()
    val eventVisitorList by viewModel.getEventVisitorListFlow().collectAsState()

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
                eventVisitorList =  eventVisitorList
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