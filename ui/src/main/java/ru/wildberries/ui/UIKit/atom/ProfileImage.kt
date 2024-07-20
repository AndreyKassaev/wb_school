package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ru.wb.domain.model.Profile
import ru.wildberries.R
import ru.wildberries.ui.theme.WBTheme

enum class ProfileState {
    None,
    Edit,
    Add
}

@Composable
fun ProfileImage (
    imageUrl: String?,
    profileState: ProfileState,
    size: Dp,
    onClick: ()->Unit
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(coil.size.Size.ORIGINAL) //necessary to specify the size, otherwise the state is always loading
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop //necessary to determine the correct dimensions to load the image at.
    )
    Box(
        modifier = Modifier
            .size(size)
    ){
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(WBTheme.colors.NeutralOffWhite),
            contentAlignment = Alignment.Center
        ){
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator()
                }
                is AsyncImagePainter.State.Error, AsyncImagePainter.State.Empty -> {
                    if (imageUrl != null){
                        Text(text = "Oops...")
                    }
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(if (profileState == ProfileState.None) 1f else 0.56f)
                            .align(Alignment.Center),
                        painter = painter,
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop
                    )
                }
            }
            if (imageUrl == null){
                Image(
                    modifier = Modifier
                        .fillMaxSize(if (profileState == ProfileState.None && imageUrl != null) 1f else 0.56f)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop
                )
            }
        }
        when (profileState) {
            ProfileState.None -> {}
            ProfileState.Edit ->
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(size * 0.25f),
                    onClick = {
                        onClick()
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(WBTheme.colors.NeutralActive)
                            .size(size * 0.2f)
                            .padding(5.dp)
                        ,
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit Profile",
                        tint = WBTheme.colors.NeutralOffWhite
                    )
                }
            ProfileState.Add ->
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(size * 0.25f),
                    onClick = {
                        onClick()
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            ,
                        painter = painterResource(id = R.drawable.profile_add),
                        contentDescription = "Add Profile",
                        tint = Color.Unspecified
                    )
                }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ProfileImagePrev() {
    WBTheme{
        ProfileImage(
            imageUrl = Profile.default.imageUrl,
            profileState = ProfileState.Edit,
            size = 100.dp,
            onClick = {}
        )
    }
}