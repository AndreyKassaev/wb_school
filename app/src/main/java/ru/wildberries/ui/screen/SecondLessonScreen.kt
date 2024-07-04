package ru.wildberries.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.domain.ProfileModel
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.molecule.CommunityCard
import ru.wildberries.ui.UIKit.molecule.EventCardList
import ru.wildberries.ui.UIKit.molecule.EventVisitorAvatarList
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg

@Composable
fun SecondLessonScreen(
    viewModel: MainViewModel,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = R.string.homework_two,
                navigationIcon = R.drawable.arrow_back,
                navigationIconOnClick = navigateBack,
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.More)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            EventCardList()
        }

        item {
            EventVisitorAvatarList(
                eventVisitorList = (0..13).map { if (it % 2 == 0) ProfileModel() else ProfileModel(image = R.drawable.event_user_avatar) }
            )
        }

        item {
            CommunityCard()
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProfileImage(profileState = ProfileState.Add)
                ProfileImage(profileState = ProfileState.Edit)
            }
        }
    }
}
