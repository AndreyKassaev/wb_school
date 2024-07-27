package ru.wb.domain.usecase.event

import ru.wb.domain.model.EventVisitor
import ru.wb.domain.repository.IEventRepository

interface GetEventVisitorListUseCase {

    suspend operator fun invoke(eventId: String): List<EventVisitor>

}

internal class GetEventVisitorListInteractor(
    private val eventRepository: IEventRepository
): GetEventVisitorListUseCase {

    override suspend operator fun invoke(eventId: String) =
        eventRepository.getEventVisitorList(eventId = eventId)

}