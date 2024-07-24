package ru.wb.domain.di

import org.koin.dsl.module
import ru.wb.domain.usecase.auth.RequestPinCodeInteractor
import ru.wb.domain.usecase.auth.RequestPinCodeUseCase
import ru.wb.domain.usecase.auth.ValidatePinCodeInteractor
import ru.wb.domain.usecase.auth.ValidatePinCodeUseCase
import ru.wb.domain.usecase.community.GetAllCommunityListInteractor
import ru.wb.domain.usecase.community.GetAllCommunityListUseCase
import ru.wb.domain.usecase.community.GetCommunityByIdInteractor
import ru.wb.domain.usecase.community.GetCommunityByIdUseCase
import ru.wb.domain.usecase.community.GetCommunityEventListInteractor
import ru.wb.domain.usecase.community.GetCommunityEventListUseCase
import ru.wb.domain.usecase.event.AcceptEventInvitationInteractor
import ru.wb.domain.usecase.event.AcceptEventInvitationUseCase
import ru.wb.domain.usecase.event.GetAllEventListInteractor
import ru.wb.domain.usecase.event.GetAllEventListUseCase
import ru.wb.domain.usecase.event.GetEventByIdInteractor
import ru.wb.domain.usecase.event.GetEventByIdUseCase
import ru.wb.domain.usecase.event.GetEventVisitorListInteractor
import ru.wb.domain.usecase.event.GetEventVisitorListUseCase
import ru.wb.domain.usecase.event.GetPersonalEventListInteractor
import ru.wb.domain.usecase.event.GetPersonalEventListUseCase
import ru.wb.domain.usecase.event.RevokeEventInvitationInteractor
import ru.wb.domain.usecase.event.RevokeEventInvitationUseCase
import ru.wb.domain.usecase.profile.CreateProfileInteractor
import ru.wb.domain.usecase.profile.CreateProfileUseCase
import ru.wb.domain.usecase.profile.GetProfileByIdInteractor
import ru.wb.domain.usecase.profile.GetProfileByIdUseCase

val domainModule = module {

    factory<RequestPinCodeUseCase>{
        RequestPinCodeInteractor(get())
    }

    factory<ValidatePinCodeUseCase>{
        ValidatePinCodeInteractor(get())
    }

    factory<CreateProfileUseCase>{
        CreateProfileInteractor(get())
    }

    factory<GetProfileByIdUseCase>{
        GetProfileByIdInteractor(get())
    }

    factory<GetAllEventListUseCase>{
        GetAllEventListInteractor(get())
    }

    factory<GetEventByIdUseCase>{
        GetEventByIdInteractor(get())
    }

    factory<GetEventVisitorListUseCase>{
        GetEventVisitorListInteractor(get())
    }

    factory<RevokeEventInvitationUseCase>{
        RevokeEventInvitationInteractor(get())
    }

    factory<AcceptEventInvitationUseCase>{
        AcceptEventInvitationInteractor(get())
    }

    factory<GetPersonalEventListUseCase>{
        GetPersonalEventListInteractor(get())
    }

    factory<GetAllCommunityListUseCase>{
        GetAllCommunityListInteractor(get())
    }

    factory<GetCommunityByIdUseCase>{
        GetCommunityByIdInteractor(get())
    }

    factory<GetCommunityEventListUseCase>{
        GetCommunityEventListInteractor(get())
    }
}