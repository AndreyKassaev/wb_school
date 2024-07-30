package ru.wb.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface GetPersonalEventListUseCase {

    suspend operator fun invoke(): Flow<List<Event>>

}

internal class GetPersonalEventListInteractor(
    private val eventRepository: IEventRepository
): GetPersonalEventListUseCase {

    override suspend operator fun invoke() =
        eventRepository.getPersonalEventList()

}