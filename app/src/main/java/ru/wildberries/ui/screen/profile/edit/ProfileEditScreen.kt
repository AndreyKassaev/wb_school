package ru.wildberries.ui.screen.profile.edit

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.atom.GhostButton
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.PhoneNumberVisualTransformation

enum class ProfileState {
    Preview,
    Edit,
}

@Composable
fun ProfileFirstNameLastNameField() {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WBTheme.colors.NeutralOffWhite),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(WBTheme.colors.BrandColorDark),
        value = text,
        onValueChange = {
            text = it.take(120)
        },
        textStyle = WBTheme.typography.bodyText1.copy(
            color = WBTheme.colors.NeutralBody
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
            ) {
                if (INIT_CONDITIION) {
                    Text(
                        text = stringResource(id = R.string.profile_edit_name),
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun ProfilePhoneNumberField() {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WBTheme.colors.NeutralOffWhite),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(WBTheme.colors.BrandColorDark),
        value = text,
        onValueChange = {
            text = it.take(10)
        },
        textStyle = WBTheme.typography.bodyText1.copy(
            color = WBTheme.colors.NeutralBody
        ),
        visualTransformation = PhoneNumberVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
            ) {
                if (INIT_CONDITIION) {
                    Text(
                        text = stringResource(id = R.string.profile_phone_number_mask),
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                } else {
                    Text(
                        text = "+7 ",
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )

                }
                innerTextField()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun ProfileEditScreen(
    viewModel: ProfileEditViewModel = koinViewModel()
) {

    val profileImageUrl by viewModel.getProfileImageUrlFlow().collectAsStateWithLifecycle()

    val navController = LocalNavController.current
    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let { imgUri ->
                "${System.currentTimeMillis()}.jpeg".also { filename ->
                    saveImageToInternalStorage(
                        context,
                        imgUri,
                        filename
                    ) {
                        navController.navigate(Router.PhotoScreen.withArgs(photo = filename))
                    }
                }
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen", "PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }

    val profileState by remember {
        mutableStateOf(ProfileState.Preview)
    }

    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }
    BackHandler(isFocused) {
        focusManager.clearFocus()
    }
    BackHandler() {
        //navigate back
    }
    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }
    val modalBottomSheetState = rememberModalBottomSheetState()
    BoxWithConstraints {
        Button(
            onClick = {
                isBottomSheetOpen = true
            }
        ) {
            Text(text = "open")
        }
        if (isBottomSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    isBottomSheetOpen = false
                },
                sheetState = modalBottomSheetState,
                containerColor = WBTheme.colors.BrandColorBG,
                dragHandle = {}
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .navigationBarsPadding()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    GhostButton(
                        onClick = {
                            photoPickerLauncher.launch("image/*")
                            isBottomSheetOpen = false
                        },
                        content = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.image),
                                    contentDescription = null,
                                    tint = WBTheme.colors.BrandColorDefault,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.profile_select_image),
                                    style = WBTheme.typography.subHeading2.copy(
                                        color = WBTheme.colors.BrandColorDefault
                                    )
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    GhostButton(
                        onClick = {
                            when (PackageManager.PERMISSION_GRANTED) {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) -> {
                                    // Some works that require permission
                                    Log.d("ExampleScreen", "Code requires permission")
                                    navController.navigate(Router.CameraScreen.route)
                                }

                                else -> {
                                    // Asking for permission
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            }
                        },
                        content = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.photo),
                                    contentDescription = null,
                                    tint = WBTheme.colors.BrandColorDefault,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.profile_take_photo),
                                    style = WBTheme.typography.subHeading2.copy(
                                        color = WBTheme.colors.BrandColorDefault
                                    )
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }
                .onFocusChanged { focusState ->
                    focusState.isFocused.also {
                        isFocused = true
                    }
                }
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxHeight / 2)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${context.filesDir.path}/$profileImageUrl")
                            .size(Size.ORIGINAL)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

//                    Image(
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        painter = painterResource(id = R.drawable.android),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop
//                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = when (profileState) {
                                        ProfileState.Preview -> {
                                            R.drawable.back
                                        }

                                        ProfileState.Edit -> {
                                            R.drawable.cross
                                        }
                                    }
                                ),
                                contentDescription = null,
                                tint = when (profileState) {
                                    ProfileState.Preview -> {
                                        WBTheme.colors.BrandColorDefault
                                    }

                                    ProfileState.Edit -> {
                                        WBTheme.colors.NeutralActive
                                    }
                                }
                            )
                        }
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = when (profileState) {
                                        ProfileState.Preview -> {
                                            R.drawable.edit
                                        }

                                        ProfileState.Edit -> {
                                            R.drawable.che_k
                                        }
                                    }
                                ),
                                contentDescription = null,
                                tint = WBTheme.colors.BrandColorDefault
                            )
                        }
                    }
                    Button(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp)
                            .alpha(0.6f),
                        onClick = {
                            isBottomSheetOpen = true
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WBTheme.colors.BrandColorDefault
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.profile_change_photo),
                            style = WBTheme.typography.subHeading2
                        )

                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    ProfileFirstNameLastNameField()
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfilePhoneNumberField()
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfileCityField()
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfileAboutField()
                    Spacer(modifier = Modifier.height(40.dp))
                    ProfileInterestField()
                    Spacer(modifier = Modifier.height(40.dp))
                    ProfileSocialNetworkField()
                    Spacer(modifier = Modifier.height(40.dp))
                    ProfilePrefsField()
                    Spacer(modifier = Modifier.height(40.dp))
                    ProfileDeleteField()
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }
}

@Composable
fun ProfileDeleteField() {
    GhostButton(
        content = {
            Text(
                text = stringResource(id = R.string.profile_delete),
                style = WBTheme.typography.subHeading1.copy(
                    color = WBTheme.colors.AccentDanger
                )
            )
        },
        onClick = {

        },
        modifier = Modifier.fillMaxWidth()
    )
}

data class ProfilePref(
    @StringRes val title: Int,
    val isSelected: Boolean
)

val profilePrefList = listOf(
    ProfilePref(
        title = R.string.profile_prefs_show_community_list,
        isSelected = true
    ),
    ProfilePref(
        title = R.string.profile_prefs_show_events,
        isSelected = true
    ),
    ProfilePref(
        title = R.string.profile_prefs_enable_notifications,
        isSelected = false
    ),
)

val profilePrefListFlow = MutableStateFlow(profilePrefList)

@Composable
fun ProfilePrefsField() {

    val prefs by profilePrefListFlow.collectAsStateWithLifecycle()

    Column {
        prefs.forEach { pref ->
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = pref.title),
                    style = WBTheme.typography.subHeading1.copy(
                        color = WBTheme.colors.BrandColorDefault
                    )
                )
                Switch(
                    checked = pref.isSelected,
                    onCheckedChange = {
                        profilePrefListFlow.update { profilePrefList ->
                            prefs.map { profilePref ->
                                if (profilePref.title == pref.title) profilePref.copy(
                                    isSelected = !pref.isSelected
                                ) else profilePref
                            }
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedIconColor = WBTheme.colors.BrandColorDefault,
                        checkedThumbColor = WBTheme.colors.NeutralOffWhite,
                        checkedBorderColor = WBTheme.colors.BrandColorDefault,
                        checkedTrackColor = WBTheme.colors.BrandColorDefault
                    )
                )
            }
//            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun ProfileSocialNetworkField() {
    Column {
        Text(
            text = stringResource(id = R.string.profile_social_networks),
            style = WBTheme.typography.heading2,
            color = WBTheme.colors.NeutralBody
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileSocialNetworkHabrField()
        Spacer(modifier = Modifier.height(8.dp))
        ProfileSocialNetworkTelegramField()
    }
}

@Composable
fun ProfileSocialNetworkHabrField() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WBTheme.colors.NeutralOffWhite),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(WBTheme.colors.BrandColorDark),
        value = text,
        onValueChange = {
            text = it.take(120)
        },
        textStyle = WBTheme.typography.bodyText1.copy(
            color = WBTheme.colors.NeutralBody
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.habr),
                    contentDescription = null,
                    tint = WBTheme.colors.NeutralActive,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
                if (INIT_CONDITIION) {
                    Text(
                        text = stringResource(id = R.string.habr),
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun ProfileSocialNetworkTelegramField() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WBTheme.colors.NeutralOffWhite),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(WBTheme.colors.BrandColorDark),
        value = text,
        onValueChange = {
            text = it.take(120)
        },
        textStyle = WBTheme.typography.bodyText1.copy(
            color = WBTheme.colors.NeutralBody
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.telegram),
                    contentDescription = null,
                    tint = WBTheme.colors.NeutralActive,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
                if (INIT_CONDITIION) {
                    Text(
                        text = stringResource(id = R.string.telegram),
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                }
                innerTextField()
            }
        }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileInterestField() {

    val textTagHolderListFlow = MutableStateFlow(
        listOf(
            TextTagHolder(
                text = "Android",
                isSelected = true
            ),
            TextTagHolder(
                text = "Developers",
                isSelected = true
            ),
            TextTagHolder(
                text = "Linux",
                isSelected = false
            ),
            TextTagHolder(
                text = "Jetpack Compose",
                isSelected = true
            )
        )
    )

    val textTagHolderList by textTagHolderListFlow.collectAsStateWithLifecycle()

    Column() {
        Text(
            text = stringResource(id = R.string.profile_interests),
            style = WBTheme.typography.heading2,
            color = WBTheme.colors.NeutralBody
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            textTagHolderList.forEach { item ->
                TextTag(
                    text = item.text,
                    isSelected = item.isSelected,
                    onClick = {
                        textTagHolderListFlow.update { newList ->
                            textTagHolderList.map { tag ->
                                if (tag.text == item.text) tag.copy(isSelected = !tag.isSelected) else tag
                            }
                        }
                    }
                )
            }
            TextTag(
                text = stringResource(id = R.string.profile_interests_add),
                isSelected = false,
                onClick = {
                    //navigate to interests page
                }
            )
        }
    }
}

data class TextTagHolder(
    val text: String,
    var isSelected: Boolean
)

@Composable
fun TextTag(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isSelected) WBTheme.colors.BrandColorDefault else WBTheme.colors.BrandColorLight
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            color = if (isSelected) WBTheme.colors.NeutralOffWhite else WBTheme.colors.BrandColorDefault,
            style = WBTheme.typography.subHeading2,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun ProfileCityField() {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WBTheme.colors.NeutralOffWhite),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(WBTheme.colors.BrandColorDark),
        value = text,
        onValueChange = {
            text = it.take(120)
        },
        textStyle = WBTheme.typography.bodyText1.copy(
            color = WBTheme.colors.NeutralBody
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
            ) {
                if (INIT_CONDITIION) {
                    Text(
                        text = stringResource(id = R.string.profile_city),
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun ProfileAboutField() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(WBTheme.colors.NeutralOffWhite),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(WBTheme.colors.BrandColorDark),
        value = text,
        onValueChange = {
            text = it.take(250)
        },
        textStyle = WBTheme.typography.bodyText1.copy(
            color = WBTheme.colors.NeutralBody
        ),
        maxLines = 3,
        minLines = 3,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
            ) {
                if (INIT_CONDITIION) {
                    Text(
                        text = stringResource(id = R.string.profile_about),
                        style = WBTheme.typography.bodyText1,
                        color = WBTheme.colors.NeutralActive
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun PermissionsCheck() {
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen", "PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }
    val context = LocalContext.current

    Button(
        onClick = {
            // Check permission
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) -> {
                    // Some works that require permission
                    Log.d("ExampleScreen", "Code requires permission")
                }

                else -> {
                    // Asking for permission
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }
    ) {
        Text(text = "Check and Request Permission")
    }
}

fun saveImageToInternalStorage(
    context: Context,
    uri: Uri,
    filename: String,
    onImageSaved: () -> Unit
) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
            onImageSaved()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ProfileEditScreenPreview() {
    WBTheme {
//        ProfileEditScreen(paddingValues = PaddingValues())
    }
}