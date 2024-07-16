package ru.wildberries.ui.screen.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private var _isAppReady by mutableStateOf(false)
    val isAppReady: Boolean
        get() = _isAppReady

    private fun isAppReady() {
        viewModelScope.launch {
            //imitate check up
            delay(3000L)
            _isAppReady = true
        }
    }
    init {
        isAppReady()
    }
}