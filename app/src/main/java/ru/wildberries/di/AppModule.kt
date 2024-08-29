package ru.wildberries.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.wildberries.ui.screen.auth.phone.PhoneNumberViewModel
import ru.wildberries.ui.screen.auth.pincode.PinCodeViewModel
import ru.wildberries.ui.screen.community.detail.CommunityDetailViewModel
import ru.wildberries.ui.screen.community.list.CommunityListViewModel
import ru.wildberries.ui.screen.event.detail.EventDetailViewModel
import ru.wildberries.ui.screen.event.list.EventListViewModel
import ru.wildberries.ui.screen.event.personal.PersonalEventListViewModel
import ru.wildberries.ui.screen.lesson.LessonViewModel
import ru.wildberries.ui.screen.more.MoreViewModel
import ru.wildberries.ui.screen.profile.create.ProfileCreateViewModel
import ru.wildberries.ui.screen.profile.detail.ProfileDetailViewModel
import ru.wildberries.ui.screen.profile.edit.ProfileEditViewModel
import ru.wildberries.ui.screen.splash.SplashViewModel
import ru.wildberries.util.VerifyPinCodeNotificationService

val appModule = module {

    viewModelOf(::SplashViewModel)

    viewModelOf(::PinCodeViewModel)

    viewModelOf(::PhoneNumberViewModel)

    viewModelOf(::ProfileCreateViewModel)

    viewModelOf(::LessonViewModel)

    viewModelOf(::ProfileDetailViewModel)

    viewModelOf(::MoreViewModel)

    viewModelOf(::EventListViewModel)

    viewModelOf(::EventDetailViewModel)

    viewModelOf(::PersonalEventListViewModel)

    viewModelOf(::CommunityDetailViewModel)

    viewModelOf(::CommunityListViewModel)

    viewModelOf(::ProfileEditViewModel)

    singleOf(::VerifyPinCodeNotificationService)

}