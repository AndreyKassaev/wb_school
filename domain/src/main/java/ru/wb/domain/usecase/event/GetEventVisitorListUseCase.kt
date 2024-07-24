package ru.wb.domain.usecase.event

import ru.wb.domain.repository.IEventRepository

class GetEventVisitorListUseCase(
    private val eventRepository: IEventRepository
) {

    suspend operator fun invoke(eventId: String) =
        eventRepository.getEventVisitorList(eventId = eventId)

}