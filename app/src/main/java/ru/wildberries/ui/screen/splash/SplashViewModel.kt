package ru.wildberries.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(): ViewModel() {

    private var isAppReadyMutable = MutableStateFlow(false)
    private val isAppReady: StateFlow<Boolean> = isAppReadyMutable

    fun getIsAppReadyFlow() = isAppReady

    init {
        initApp()
    }

    private fun initApp() {
        viewModelScope.launch {
            //imitate check up
            delay(DELAY)
            isAppReadyMutable.update {
                true
            }
        }
    }

}

const val DELAY = 3000L