package ru.wb.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface GetEventByIdUseCase {

    suspend operator fun invoke(eventId: String): Flow<Event>

}

internal class GetEventByIdInteractor(
    private val eventRepository: IEventRepository
): GetEventByIdUseCase {

    override suspend operator fun invoke(eventId: String) =
        eventRepository.getEventById(eventId = eventId)

}