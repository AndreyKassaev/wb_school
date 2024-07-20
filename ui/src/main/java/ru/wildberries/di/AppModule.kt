package ru.wildberries.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.wb.domain.Repository
import ru.wb.repository.MockRepositoryImpl
import ru.wildberries.ui.screen.auth.AuthViewModel
import ru.wildberries.ui.screen.community.CommunityViewModel
import ru.wildberries.ui.screen.event.EventViewModel
import ru.wildberries.ui.screen.lesson.LessonViewModel
import ru.wildberries.ui.screen.profile.ProfileViewModel
import ru.wildberries.ui.screen.splash.SplashViewModel

val appModule = module {
    single<Repository> {
        MockRepositoryImpl()
    }
    viewModel<SplashViewModel>{
        SplashViewModel()
    }
    viewModel<AuthViewModel>{
        AuthViewModel(repository = get())
    }
    viewModel<LessonViewModel>{
        LessonViewModel(repository = get())
    }
    viewModel<CommunityViewModel>{
        CommunityViewModel(repository = get())
    }
    viewModel<EventViewModel>{
        EventViewModel(repository = get())
    }
    viewModel<ProfileViewModel>{
        ProfileViewModel(repository = get())
    }
}