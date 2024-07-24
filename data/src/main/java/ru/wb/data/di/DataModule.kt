package ru.wb.data.di

import org.koin.dsl.module
import ru.wb.data.repository.AuthRepository
import ru.wb.data.repository.CommunityRepository
import ru.wb.data.repository.EventRepository
import ru.wb.data.repository.ProfileRepository
import ru.wb.domain.repository.IAuthRepository
import ru.wb.domain.repository.ICommunityRepository
import ru.wb.domain.repository.IEventRepository
import ru.wb.domain.repository.IProfileRepository

val dataModule = module {

    single<IAuthRepository> {
        AuthRepository()
    }

    single<IProfileRepository> {
        ProfileRepository()
    }

    single<IEventRepository> {
        EventRepository()
    }

    single<ICommunityRepository> {
        CommunityRepository()
    }

}