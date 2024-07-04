package ru.wildberries.ui.UIKit.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.domain.ProfileModel
import ru.wildberries.ui.theme.WBTheme

enum class ProfileState {
    None,
    Edit,
    Add
}

@Composable
fun ProfileImage (
    image: Int = R.drawable.profile,
    profileState: ProfileState = ProfileState.None,
    size: Dp = 100.dp,
    onClick: ()->Unit = {}
) {
    Box(
        modifier = Modifier
            .size(size)
    ){
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(WBTheme.colors.NeutralOffWhite)
                .align(Alignment.Center),
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize(if (profileState == ProfileState.None) 1f else 0.56f)
                    .align(Alignment.Center),
                painter = painterResource(id = image),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop
            )
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
        ProfileImage()
    }
}