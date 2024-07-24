package ru.wb.domain.usecase.event

import ru.wb.domain.repository.IEventRepository

class GetEventByIdUseCase(
    private val eventRepository: IEventRepository
) {

    suspend operator fun invoke(eventId: String) =
        eventRepository.getEventById(eventId = eventId)

}