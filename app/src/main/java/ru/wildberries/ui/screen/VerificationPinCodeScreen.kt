package ru.wildberries.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.wildberries.R
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.atom.GhostButton
import ru.wildberries.ui.UIKit.molecule.PinCodeField
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.phoneNumberFormatter

@Composable
fun VerificationPinCodeScreen(
    viewModel: MainViewModel,
    navController: NavHostController
) {
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
                .offset(x = (-32).dp),
            navigationIconOnClick = { navController.popBackStack() }
        )
        Text(
            modifier = Modifier
                .padding(
                    bottom = 8.dp,
                    top = 79.dp
                ),
            text = stringResource(id = R.string.enter_pin_code),
            style = WBTheme.typography.heading2,
            color = WBTheme.colors.NeutralActive
        )
        Text(
            text = stringResource(id = R.string.enter_pin_code_desc),
            style = WBTheme.typography.bodyText2,
            color = WBTheme.colors.NeutralActive
        )
        Text(
            text = phoneNumberFormatter("${viewModel.verificationPhoneNumberCountryCode}${viewModel.verificationPhoneNumber}"),
            style = WBTheme.typography.bodyText2,
            color = WBTheme.colors.NeutralActive
        )
        PinCodeField(
            viewModel = viewModel,
            navController = navController
        )
        GhostButton(
            content = {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(id = R.string.enter_pin_code_request_again),
                    style = WBTheme.typography.subHeading2
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 69.dp),
            onClick = {
                viewModel.setVerificationPinCode("")
            },
            isEnabled = viewModel.verificationPinCode.length == 4
        )
    }
}