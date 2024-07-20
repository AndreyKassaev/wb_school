package ru.wildberries.ui.screen.auth

import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.wildberries.R
import ru.wildberries.ui.UIKit.atom.GhostButton
import ru.wildberries.ui.UIKit.molecule.PinCodeField
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme
import java.util.Locale

@Composable
fun VerificationPinCodeScreen(
    viewModel: AuthViewModel,
    navController: NavHostController
) {

    val phoneNumber by viewModel.getPhoneNumberFlow().collectAsState()

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
            modifier = Modifier.padding(bottom = 50.dp),
            text = PhoneNumberUtils.formatNumber(
                "${phoneNumber.countryCode.code}${phoneNumber.number}",
                Locale.getDefault().country
            ),
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
        )
    }
}