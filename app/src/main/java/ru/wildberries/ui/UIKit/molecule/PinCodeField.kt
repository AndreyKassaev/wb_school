package ru.wildberries.ui.UIKit.molecule

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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.wildberries.R
import ru.wildberries.navigation.EventsRoute
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.theme.WBTheme

@Composable
fun PinCodeField(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    var textFieldValue by remember() {
        mutableStateOf(TextFieldValue("" ))
    }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BasicTextField(
        modifier = Modifier
            .focusRequester(focusRequester),
        value = textFieldValue,
        onValueChange = {
            if (it.text.length <= 4) {
                textFieldValue = it
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = { navController.navigate(EventsRoute) }
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(4){
                    PinCodeDot(value = textFieldValue.text, index = it)
                }
            }
        }
    )
}

@Composable
fun PinCodeDot(value: String, index: Int) {
    Box(
        modifier = Modifier
            .size(40.dp),
        contentAlignment = Alignment.Center
    ){
        if (value.getOrNull(index) != null){
            Text(
                text = value.getOrNull(index).toString(),
                style = WBTheme.typography.heading1,
                color = WBTheme.colors.NeutralActive
            )
        }else{
            Icon(
                painter = painterResource(id = R.drawable.pin_code),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}