package ru.wildberries.ui.screen.auth.phone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.wildberries.R
import ru.wildberries.navigation.LocalNavController
import ru.wildberries.navigation.Router
import ru.wildberries.ui.UIKit.atom.PrimaryButton
import ru.wildberries.ui.UIKit.molecule.PhoneNumberField
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
internal fun PhoneNumberScreen(
    viewModel: PhoneNumberViewModel = koinViewModel()
) {

    val navController = LocalNavController.current
    val isPhoneNumberValid by viewModel.getIsPhoneNumberValidFlow()
        .collectAsStateWithLifecycle(false)
    val phoneNumber by viewModel.getPhoneNumberFlow()
        .collectAsStateWithLifecycle()
    val phoneCountryCodeList = viewModel.countryCodeLists

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            navigationIcon = R.drawable.arrow_back,
            modifier = Modifier
                .padding(16.dp)
                .offset(x = (-32).dp)
        )
        Text(
            modifier = Modifier
                .padding(
                    bottom = 8.dp,
                    top = 79.dp
                ),
            text = stringResource(id = R.string.enter_phone_number),
            style = WBTheme.typography.heading2,
            color = WBTheme.colors.NeutralActive
        )
        Text(
            modifier = Modifier.padding(bottom = 49.dp),
            text = stringResource(id = R.string.enter_phone_number_desc),
            style = WBTheme.typography.bodyText2,
            color = WBTheme.colors.NeutralActive
        )
        PhoneNumberField(
            phoneNumber = phoneNumber,
            isPhoneNumberValid = isPhoneNumberValid,
            countryCodeList = phoneCountryCodeList,
            setPhoneCountryCode = viewModel::setPhoneCountryCode,
            setPhoneNumber = viewModel::setPhoneNumber,
            navigateToPinCodeScreen = {
                viewModel.requestPinCode()
                navController.navigate(Router.VerificationPinCode.withArg(phoneNumber = "${phoneNumber.countryCode.code}${phoneNumber.number}"))
            },
        )
        PrimaryButton(
            content = {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(id = R.string.enter_phone_number_continue),
                    style = WBTheme.typography.subHeading2
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 69.dp),
            onClick = {
                viewModel.requestPinCode()
                navController.navigate(Router.VerificationPinCode.withArg(phoneNumber = "${phoneNumber.countryCode.code}${phoneNumber.number}"))
            },
            isEnabled = isPhoneNumberValid
        )
    }
}