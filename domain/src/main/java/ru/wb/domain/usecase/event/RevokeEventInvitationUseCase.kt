package ru.wb.domain.usecase.event

import ru.wb.domain.model.EventVisitor
import ru.wb.domain.repository.IEventRepository

interface RevokeEventInvitationUseCase {

    suspend operator fun invoke(eventId: String): List<EventVisitor>

}

internal class RevokeEventInvitationInteractor(
    private val eventRepository: IEventRepository
): RevokeEventInvitationUseCase {

    override suspend operator fun invoke(eventId: String) =
        eventRepository.revokeEventInvitation(eventId = eventId)

}