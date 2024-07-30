package ru.wb.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface RevokeEventInvitationUseCase {

    suspend operator fun invoke(eventId: String): Flow<Event>

}

internal class RevokeEventInvitationInteractor(
    private val eventRepository: IEventRepository
): RevokeEventInvitationUseCase {

    override suspend operator fun invoke(eventId: String) =
        eventRepository.removeUserFromEventVisitorList(eventId = eventId)

}