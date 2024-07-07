package ru.wildberries.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ru.wildberries.R
import ru.wildberries.domain.EventModel
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.PrimaryButton
import ru.wildberries.ui.UIKit.molecule.EventVisitorAvatarList
import ru.wildberries.ui.UIKit.molecule.TagRow
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme

@Composable
fun EventDetailScreen(
    viewModel: MainViewModel,
    event: EventModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = event.title,
                navigationIcon = R.drawable.arrow_back,
                navigationIconOnClick = { navController.popBackStack() },
                actionIcon = null,
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.Events)
    }
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(event.imageUrl)
            .size(coil.size.Size.ORIGINAL) //necessary to specify the size, otherwise the state is always loading
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop //necessary to determine the correct dimensions to load the image at.
    )
    val eventVisitorList by viewModel.eventVisitorList.collectAsState()
    var isFullScreen by rememberSaveable {
        mutableStateOf(false)
    }
    if (isFullScreen){
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
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp),
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                text = "${event.date} - ${event.location}",
                style = WBTheme.typography.bodyText1,
                color = WBTheme.colors.NeutralWeak,
            )
            TagRow(tagList = event.tagList)
        }
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
        item {
            EventVisitorAvatarList(eventVisitorList = eventVisitorList)
        }
        item {
            PrimaryButton(
                content = { 
                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        text = stringResource(id = R.string.ill_go),
                    )
                },
                onClick = {},
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