package ru.wb.domain.usecase.event

import ru.wb.domain.model.EventVisitor
import ru.wb.domain.repository.IEventRepository

interface AcceptEventInvitationUseCase {

    suspend operator fun invoke(eventId: String): List<EventVisitor>
}

internal class AcceptEventInvitationInteractor(
    private val eventRepository: IEventRepository
): AcceptEventInvitationUseCase {

    override suspend operator fun invoke(eventId: String) =
        eventRepository.acceptEventInvitation(eventId = eventId)

}