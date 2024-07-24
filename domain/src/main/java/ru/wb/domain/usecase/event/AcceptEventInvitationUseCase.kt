package ru.wb.domain.usecase.event

import ru.wb.domain.repository.IEventRepository

class AcceptEventInvitationUseCase(
    private val eventRepository: IEventRepository
) {

    suspend operator fun invoke(eventId: String) =
        eventRepository.acceptEventInvitation(eventId = eventId)

}