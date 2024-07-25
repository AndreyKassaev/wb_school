package ru.wildberries.ui.screen.event.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.ui.UIKit.atom.PrimaryButton
import ru.wildberries.ui.UIKit.atom.SecondaryButton
import ru.wildberries.ui.UIKit.molecule.EventVisitorAvatarList
import ru.wildberries.ui.UIKit.molecule.TagRow
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun EventDetailScreen(
    viewModel: EventDetailViewModel = koinViewModel()
) {

    val navController =LocalNavController.current
    val event by viewModel.getCurrentEventFlow().collectAsState()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(event.imageUrl)
            .size(coil.size.Size.ORIGINAL) //necessary to specify the size, otherwise the state is always loading
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop //necessary to determine the correct dimensions to load the image at.
    )
    val eventVisitorList by viewModel.getEventVisitorListFlow().collectAsState()
    var isFullScreen by rememberSaveable {
        mutableStateOf(false)
    }
    val isInvitationAccepted by viewModel.getIsInvitationAcceptedFlow().collectAsState()

    if (isFullScreen) {
        Dialog(
            onDismissRequest = {
                isFullScreen = false
            }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            )
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp),
    ) {
        TopBar(
            title = event.title,
            navigationIcon = R.drawable.arrow_back,
            actionIcon = {
                if (isInvitationAccepted) {
                    Icon(
                        painter = painterResource(R.drawable.invite_accepted),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            },
            navigationIconOnClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(
                    bottom = 29.dp,
                    top = 16.dp
                )
                .offset(x = (-12).dp)
        )
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = "${event.date} - ${event.location}",
            style = WBTheme.typography.bodyText1,
            color = WBTheme.colors.NeutralWeak,
        )
        TagRow(tagList = event.tagList)
        LazyColumn(
            modifier = Modifier
                .weight(0.75f)
                .padding(top = 8.dp)
        ) {
            item {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator()
                    }

                    is AsyncImagePainter.State.Error, AsyncImagePainter.State.Empty -> {
                        Text(text = "Oops...")
                    }

                    is AsyncImagePainter.State.Success -> {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(
                                    width = 400.dp,
                                    height = 300.dp
                                )
                                .padding(
                                    top = 12.dp,
                                    bottom = 20.dp
                                )
                                .clip(RoundedCornerShape(24))
                                .clickable {
                                    isFullScreen = !isFullScreen
                                },
                            painter = painter,
                            contentDescription = "Profile",
                            contentScale = ContentScale.FillHeight,
                        )
                    }
                }
            }
            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    text = event.description,
                    style = WBTheme.typography.metadata1,
                    color = WBTheme.colors.NeutralWeak
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.25f)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            EventVisitorAvatarList(eventVisitorList = eventVisitorList)
        }
        when(event.isActive){
            true -> SwitchEventInviteButton(
                isInvitationAccepted = isInvitationAccepted,
                acceptEventInvitation = viewModel::acceptEventInvitation,
                revokeEventInvitation = viewModel::revokeEventInvitation
            )
            false -> PrimaryButton(
                content = {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        text = stringResource(id = R.string.my_events_tabitem_finished)
                    )
                },
                onClick = {},
                isEnabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 20.dp
                    )
            )
        }
    }
}

@Composable
internal fun SwitchEventInviteButton(
    isInvitationAccepted: Boolean,
    acceptEventInvitation: () -> Unit,
    revokeEventInvitation: () -> Unit,
) {

    when (isInvitationAccepted) {
        true -> SecondaryButton(
            content = {
                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    text = stringResource(R.string.ill_go_next_time),
                )
            },
            onClick = {
                revokeEventInvitation()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 20.dp
                )
        )

        false -> PrimaryButton(
            content = {
                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    text = stringResource(R.string.ill_go),
                )
            },
            onClick = {
                acceptEventInvitation()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 20.dp
                )
        )
    }

}