package ru.wb.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.wb.domain.usecase.auth.RequestPinCodeUseCase
import ru.wb.domain.usecase.auth.ValidatePinCodeUseCase
import ru.wb.domain.usecase.community.GetAllCommunityListUSeCase
import ru.wb.domain.usecase.community.GetCommunityByIdUseCase
import ru.wb.domain.usecase.community.GetCommunityEventListUSeCase
import ru.wb.domain.usecase.event.AcceptEventInvitationUseCase
import ru.wb.domain.usecase.event.GetAllEventListUseCase
import ru.wb.domain.usecase.event.GetEventByIdUseCase
import ru.wb.domain.usecase.event.GetEventVisitorListUseCase
import ru.wb.domain.usecase.event.GetPersonalEventListUseCase
import ru.wb.domain.usecase.event.RevokeEventInvitationUseCase
import ru.wb.domain.usecase.profile.CreateProfileUseCase
import ru.wb.domain.usecase.profile.GetProfileByIdUseCase

val domainModule = module {

    factoryOf(::RequestPinCodeUseCase)

    factoryOf(::ValidatePinCodeUseCase)

    factoryOf(::CreateProfileUseCase)

    factoryOf(::GetProfileByIdUseCase)

    factoryOf(::GetAllEventListUseCase)

    factoryOf(::GetEventByIdUseCase)

    factoryOf(::GetEventVisitorListUseCase)

    factoryOf(::RevokeEventInvitationUseCase)

    factoryOf(::AcceptEventInvitationUseCase)

    factoryOf(::GetPersonalEventListUseCase)

    factoryOf(::GetAllCommunityListUSeCase)

    factoryOf(::GetCommunityByIdUseCase)

    factoryOf(::GetCommunityEventListUSeCase)
}