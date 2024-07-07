package ru.wildberries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.wildberries.navigation.Navigation
import ru.wildberries.ui.theme.WBTheme
import ru.wildberries.util.ActivityContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityContext.setActivityContext(this)
        enableEdgeToEdge()
        setContent {
            WBTheme {
                Navigation()
            }
        }
    }
}