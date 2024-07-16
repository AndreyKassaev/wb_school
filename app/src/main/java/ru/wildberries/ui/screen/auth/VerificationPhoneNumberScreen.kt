package ru.wildberries.ui.screen.auth

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
import androidx.navigation.NavController
import ru.wildberries.R
import ru.wildberries.navigation.VerificationPinCodeRoute
import ru.wildberries.ui.UIKit.atom.PrimaryButton
import ru.wildberries.ui.UIKit.molecule.PhoneNumberField
import ru.wildberries.ui.UIKit.organism.TopBar
import ru.wildberries.ui.theme.WBTheme

@Composable
fun VerificationPhoneNumberScreen(
    viewModel: AuthViewModel,
    navController: NavController
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
                .offset(x = (-32).dp)
        )
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp, top = 79.dp),
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
            viewModel = viewModel,
            navController = navController,
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
                navController.navigate(VerificationPinCodeRoute)
            },
            isEnabled = viewModel.verificationPhoneNumber.length == 10
        )
    }
}