package ru.wildberries.ui.screen.profile.detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.wb.domain.usecase.profile.GetProfileByIdUseCase
import ru.wildberries.ui.model.Profile
import ru.wildberries.ui.model.toUiProfile
import java.io.File

internal class ProfileDetailViewModel(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val context: Context
): ViewModel() {

    private var profileMutable = MutableStateFlow(Profile.default)
    private val profile: StateFlow<Profile> = profileMutable

    init {
        initProfileData()
    }


    fun getProfileFlow() = profile

    private fun initProfileData() {
        println("initProfileData")
        viewModelScope.launch {
            getProfileByIdUseCase(profileId = "").collectLatest { profile ->
                println("GET" + profile.imageUrl)
                profileMutable.update {
                    profile.toUiProfile()
                }
            }
        }
    }

}