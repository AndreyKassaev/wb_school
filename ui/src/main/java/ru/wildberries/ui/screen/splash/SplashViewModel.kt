package ru.wildberries.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private var _isAppReady = MutableStateFlow(false)
    val isAppReady = _isAppReady.asStateFlow()

    private fun isAppReady() {
        viewModelScope.launch {
            //imitate check up
            delay(DELAY)
            _isAppReady.update {
                true
            }
        }
    }
    init {
        isAppReady()
    }
}

const val DELAY = 3000L