package ru.wildberries.ui.UIKit.molecule

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.wildberries.R
import ru.wildberries.ui.theme.WBTheme

@Composable
fun PinCodeField(
    pinCode: String,
    isPinCodeValid: Boolean,
    setVerificationPinCode: (String) -> Unit,
    navigateToCreateProfile: () -> Unit
) {

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    var isError by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isError) {
        delay(500L)
        isError = false
    }

    BasicTextField(
        modifier = Modifier
            .focusRequester(focusRequester),
        value = pinCode,
        onValueChange = {
            setVerificationPinCode(it.take(4))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                if (isPinCodeValid) {
                    navigateToCreateProfile()
                } else {
                    isError = true
                }
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(4) {
                    PinCodeDot(
                        value = pinCode,
                        index = it,
                        isError = isError
                    )
                }
            }
        }
    )
}

@Composable
fun PinCodeDot(
    value: String,
    index: Int,
    isError: Boolean
) {
    val scale by animateFloatAsState(if (isError) 1.5f else 1f)

    Box(
        modifier = Modifier
            .size(40.dp)
            .scale(scale),
        contentAlignment = Alignment.Center
    ) {
        if (value.getOrNull(index) != null) {
            Text(
                text = value.getOrNull(index)
                    .toString(),
                style = WBTheme.typography.heading1,
                color = if (isError) WBTheme.colors.AccentDanger else WBTheme.colors.NeutralActive
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.pin_code),
                contentDescription = null,
                tint = if (isError) WBTheme.colors.AccentDanger else Color.Unspecified
            )
        }
    }
}