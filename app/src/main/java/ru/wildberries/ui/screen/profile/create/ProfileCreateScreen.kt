package ru.wildberries.ui.screen.profile.create

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.atom.PrimaryButton
import ru.wildberries.ui.UIKit.atom.ProfileImage
import ru.wildberries.ui.UIKit.atom.ProfileState
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun ProfileCreateScreen(
    viewModel: ProfileCreateViewModel = koinViewModel(),
) {
    var result by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result = it.toString()
    }
    val navController = LocalNavController.current
    var firstName by rememberSaveable {
        mutableStateOf("")
    }
    var lastName by rememberSaveable {
        mutableStateOf("")
    }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BackHandler {
        navController.navigate(Router.Base.Auth.route) {
            popUpTo(Router.Base.Auth.route) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = stringResource(id = R.string.topappbar_title_profile),
            navigationIcon = R.drawable.arrow_back,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 46.dp
                )
                .offset(x = (-12).dp),
            navigationIconOnClick = {
                navController.navigate(Router.Base.Auth.route) {
                    popUpTo(Router.Base.Auth.route) {
                        inclusive = true
                    }
                }
            }
        )
        ProfileImage(
            imageUrl = result,
            profileState = ProfileState.Add,
            size = 100.dp,
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        )
        BasicTextField(
            modifier = Modifier
                .padding(
                    top = 31.dp,
                    bottom = 12.dp
                )
                .focusRequester(focusRequester),
            value = firstName,
            onValueChange = {
                firstName = it
            },
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .fillMaxWidth()
                        .background(WBTheme.colors.NeutralOffWhite)
                        .padding(8.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(0.01f)){
                        innerTextField()
                    }
                    if (firstName.isEmpty()){
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = stringResource(id = R.string.firstName_required),
                            style = WBTheme.typography.bodyText1,
                            color = WBTheme.colors.NeutralDisabled
                        )
                    }
                }
            }
        )
        BasicTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .fillMaxWidth()
                        .background(WBTheme.colors.NeutralOffWhite)
                        .padding(8.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier.weight(0.01f)){
                        innerTextField()
                    }
                    if (lastName.isEmpty()){
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = stringResource(id = R.string.lastName_required),
                            style = WBTheme.typography.bodyText1,
                            color = WBTheme.colors.NeutralDisabled
                        )
                    }
                }
            }
        )
        PrimaryButton(
            content = {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(id = R.string.save),
                    style = WBTheme.typography.subHeading2
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp),
            onClick = {
                viewModel.createProfile(
                    firstName = firstName,
                    lastName = lastName,
                    imageUrl = result ?: ""
                )
                navController.navigate(Router.Base.Event.route){
                    popUpTo(Router.Base.Auth.route){
                        inclusive = true
                    }
                }
            },
            isEnabled = firstName != ""
        )
    }
}