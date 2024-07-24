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
import androidx.compose.ui.unit.dp
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.ui.model.CountryCode
import ru.wildberries.ui.model.PhoneNumber
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.PhoneNumberVisualTransformation

@Composable
internal fun PhoneNumberField(
    phoneNumber: PhoneNumber,
    isPhoneNumberValid: Boolean,
    countryCodeList: List<CountryCode>,
    setPhoneNumber: (String) -> Unit,
    setPhoneCountryCode: (CountryCode) -> Unit,
    navigateToPinCodeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    val navController = LocalNavController.current
    var expanded by remember { mutableStateOf(false) }
    var selectedPhoneCountryCode by remember {
        mutableStateOf(countryCodeList.first())
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
            countryCodeList.forEachIndexed { index, phoneCountryCode ->
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
                        setPhoneCountryCode(phoneCountryCode)
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
                setPhoneNumber(it.take(10))
            },
            textStyle = WBTheme.typography.bodyText1.copy(
                color = WBTheme.colors.NeutralActive
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { if (isPhoneNumberValid) navigateToPinCodeScreen() }
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