package ru.wildberries.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.wildberries.R
import ru.wildberries.navigation.VerificationPhoneRoute
import ru.wildberries.ui.MainViewModel
import ru.wildberries.ui.theme.WBTheme


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAppReady = viewModel.isAppReady
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.splash_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WBTheme.colors.BrandColorBG),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = {progress}
        )
    }
    if (isAppReady){
        navController.navigate(VerificationPhoneRoute)
    }
}