package ru.wildberries.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.wb.repository.MockRepositoryImpl
import ru.wildberries.R
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.atom.SecondaryButton
import ru.wildberries.ui.UIKit.molecule.Profile
import ru.wildberries.ui.UIKit.molecule.ProfileMode
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navController: NavController
) {
    val profileData by viewModel.profile.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TopBar(
            title = stringResource(id = R.string.topappbar_title_profile),
            navigationIcon = R.drawable.arrow_back,
            actionIcon = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = null
                    )
                }
            },
            navigationIconOnClick = {navController.popBackStack()},
            modifier = Modifier.padding(end = 8.dp, top = 16.dp, bottom = 46.dp)
        )
        Profile(
            profileData = profileData,
            profileMode = ProfileMode.FullScreen,
            profileState = ProfileState.None,
            onClick = {},
            size = 200.dp
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
        ProfileScreen(
            viewModel = ProfileViewModel(MockRepositoryImpl()),
            navController = rememberNavController()
        )
    }
}