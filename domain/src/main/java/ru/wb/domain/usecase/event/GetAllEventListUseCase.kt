package ru.wb.domain.usecase.event

import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface GetAllEventListUseCase {

    suspend operator fun invoke(): List<Event>

}

internal class GetAllEventListInteractor(
    private val eventRepository: IEventRepository
): GetAllEventListUseCase {

    override suspend operator fun invoke() =
        eventRepository.getAllEventList()

}