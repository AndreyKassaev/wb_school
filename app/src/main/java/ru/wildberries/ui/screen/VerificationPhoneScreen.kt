package ru.wildberries.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.molecule.PhoneNumberField
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg

@Composable
fun VerificationPhoneScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.setTopAppBar(
            TopBarArg(
                title = "",
                navigationIcon = null,
                actionIcon = null
            )
        )
        viewModel.setSelectedBottomAppBarItem(BottomAppBarItem.None)
    }
    PhoneNumberField(
        viewModel = viewModel,
        navController = navController
    )
}