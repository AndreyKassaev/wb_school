package ru.wb.domain.usecase.event

import ru.wb.domain.repository.IEventRepository

class RevokeEventInvitationUseCase(
    private val eventRepository: IEventRepository
) {

    suspend operator fun invoke(eventId: String) =
        eventRepository.revokeEventInvitation(eventId = eventId)

}