package ru.wildberries.ui.UIKit.atom

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.wildberries.R
import ru.wildberries.ui.theme.WBTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SearchBar() {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var text by remember {
        mutableStateOf("")
    }
    val INIT_CONDITIION = text.isEmpty() && !isFocused

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(WBTheme.colors.NeutralOffWhite)
                .weight(1f),
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
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SearchBarPlaceHolder(
                        INIT_CONDITIION = INIT_CONDITIION
                    )
                    SearchBarContent(
                        innerTextField = innerTextField,
                        INIT_CONDITIION = INIT_CONDITIION,
                        onCancel = { text = "" }
                    )
                }
            }
        )

        SearchBarAction(
            INIT_CONDITIION = INIT_CONDITIION,
            onCancel = {
                text = ""
                focusManager.clearFocus()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarAction(
    INIT_CONDITIION: Boolean,
    onCancel: () -> Unit,
) {
    if (INIT_CONDITIION) {
        CompositionLocalProvider(
            LocalMinimumInteractiveComponentEnforcement provides false,
        ) {
            IconButton(
                onClick = {

                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    tint = WBTheme.colors.BrandColorDefault,
                )
            }
        }
    } else {
        GhostButton(
            content = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = WBTheme.typography.bodyText1,
                    maxLines = 1
                )
            },
            onClick = {
                onCancel()
            },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBarContent(
    innerTextField: @Composable () -> Unit,
    INIT_CONDITIION: Boolean,
    onCancel: () -> Unit
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, top = 16.dp, bottom = 16.dp)
                .weight(1f)
        ) {
            innerTextField()
        }
        if (!INIT_CONDITIION) {
            CompositionLocalProvider(
                LocalMinimumInteractiveComponentEnforcement provides false,
            ) {
                IconButton(
                    onClick = {
                        onCancel()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cross),
                        contentDescription = null,
                        tint = WBTheme.colors.NeutralActive
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBarPlaceHolder(INIT_CONDITIION: Boolean) {
    if (INIT_CONDITIION) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp),
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = null,
            tint = WBTheme.colors.NeutralActive
        )
        Text(
            text = stringResource(id = R.string.search_bar),
            style = WBTheme.typography.bodyText1,
            color = WBTheme.colors.NeutralActive
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SearchBarPreview() {
    WBTheme {
        SearchBar()
    }
}