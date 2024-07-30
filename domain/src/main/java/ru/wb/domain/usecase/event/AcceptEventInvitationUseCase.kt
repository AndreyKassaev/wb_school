package ru.wb.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface AcceptEventInvitationUseCase {

    suspend operator fun invoke(eventId: String): Flow<Event>

}

internal class AcceptEventInvitationInteractor(
    private val eventRepository: IEventRepository
): AcceptEventInvitationUseCase {

    override suspend operator fun invoke(eventId: String) =
        eventRepository.addUserToEventVisitorList(eventId = eventId)

}