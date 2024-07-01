package ru.wildberries.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.data.MockRepositoryImpl
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.atom.SecondaryButton
import ru.wildberries.ui.UIKit.molecule.Profile
import ru.wildberries.ui.UIKit.molecule.ProfileMode
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg
import ru.wildberries.ui.theme.WBTheme

@Composable
fun ProfileAccountScreen(
    viewModel: MainViewModel,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = R.string.topappbar_title_profile,
                navigationIcon = R.drawable.arrow_back,
                navigationIconOnClick = navigateBack,
                actionIcon = R.drawable.edit
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.More)
    }

    val profileData = viewModel.profileData

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 46.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Profile(
            profileData = profileData,
            profileMode = ProfileMode.FullScreen
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "",
                    )
                },
                onClick = {}
            )
            SecondaryButton(
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "",
                    )
                },
                onClick = {}
            )
            SecondaryButton(
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = "",
                    )
                },
                onClick = {}
            )
            SecondaryButton(
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "",
                    )
                },
                onClick = {}
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ProfileAccountScreenPreview() {
    WBTheme {
        ProfileAccountScreen(
            viewModel = MainViewModel(MockRepositoryImpl()),
            navigateBack = {},
        )
    }
}