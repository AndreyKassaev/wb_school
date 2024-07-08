package ru.wildberries.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.UIKit.molecule.PinCodeField
import ru.wildberries.ui.UIKit.organism.BottomAppBarItem
import ru.wildberries.ui.UIKit.organism.TopBarArg

@Composable
fun VerificationPinCodeScreen(
    viewModel: MainViewModel,
    navController: NavHostController
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
    PinCodeField(
        viewModel = viewModel,
        navController = navController
    )
}