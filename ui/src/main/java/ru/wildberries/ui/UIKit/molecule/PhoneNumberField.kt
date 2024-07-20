package ru.wildberries.ui.UIKit.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.wb.repository.MockRepositoryImpl
import ru.wildberries.navigation.Route
import ru.wildberries.ui.screen.auth.AuthViewModel
import ru.wildberries.ui.screen.auth.phoneCountryCodeList
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.PhoneNumberVisualTransformation

@Composable
fun PhoneNumberField(
    viewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedPhoneCountryCode by remember {
        mutableStateOf(phoneCountryCodeList.first())
    }
    val focusRequester = remember { FocusRequester() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    expanded = !expanded
                }
                .background(WBTheme.colors.NeutralOffWhite)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = selectedPhoneCountryCode.icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                text = selectedPhoneCountryCode.code,
                style = WBTheme.typography.bodyText1,
                color = WBTheme.colors.NeutralDisabled
            )
        }
        DropdownMenu(
            modifier = Modifier
                .background(WBTheme.colors.NeutralOffWhite),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            phoneCountryCodeList.forEachIndexed { index, phoneCountryCode ->
                DropdownMenuItem(
                    modifier = Modifier
                        .background(WBTheme.colors.NeutralOffWhite),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                painter = painterResource(id = phoneCountryCode.icon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                            Text(
                                text = phoneCountryCode.code,
                                style = WBTheme.typography.bodyText1,
                                color = WBTheme.colors.NeutralDisabled
                            )
                        }
                    },
                    onClick = {
                        viewModel.setVerificationPhoneNumber(phoneCountryCode)
                        selectedPhoneCountryCode = phoneCountryCode
                        expanded = false
                    }
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            modifier = Modifier
                .focusRequester(focusRequester),
            value = phoneNumber.number,
            cursorBrush = SolidColor(Color.Transparent),
            onValueChange = {
                viewModel.setVerificationPhoneNumber(it.take(10))
            },
            textStyle = WBTheme.typography.bodyText1.copy(
                color = WBTheme.colors.NeutralActive
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { if (phoneNumber.number.length == 10) navController.navigate(Route.VerificationPinCode) }
            ),
            visualTransformation = PhoneNumberVisualTransformation(),
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
                    if (phoneNumber.number.isEmpty()) {
                        Text(
                            text = "000 000 00-00-00",
                            style = WBTheme.typography.bodyText1,
                            color = WBTheme.colors.NeutralDisabled
                        )
                    }
                    innerTextField()
                }
            }
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PhoneNumberFieldPreview() {
    WBTheme {
        PhoneNumberField(
            viewModel = AuthViewModel(MockRepositoryImpl()),
            navController = rememberNavController()
        )
    }
}