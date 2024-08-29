package ru.wildberries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import ru.wildberries.navigation.Navigation
import ru.wildberries.ui.theme.WBRippleTheme
import ru.wildberries.ui.theme.WBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
//        enableEdgeToEdge()
        setContent {
            WBTheme {
                CompositionLocalProvider(LocalRippleTheme provides WBRippleTheme) {
                    Navigation()
                }
            }
        }
    }
}