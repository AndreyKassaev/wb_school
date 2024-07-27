package ru.wb.domain.usecase.event

import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface GetPersonalEventListUseCase {

    suspend operator fun invoke(): List<Event>

}

internal class GetPersonalEventListInteractor(
    private val eventRepository: IEventRepository
): GetPersonalEventListUseCase {

    override suspend operator fun invoke() =
        eventRepository.getPersonalEventList()

}