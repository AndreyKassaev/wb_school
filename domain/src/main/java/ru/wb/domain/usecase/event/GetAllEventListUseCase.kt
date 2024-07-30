package ru.wb.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

interface GetAllEventListUseCase {

    suspend operator fun invoke(): Flow<List<Event>>

}

internal class GetAllEventListInteractor(
    private val eventRepository: IEventRepository
): GetAllEventListUseCase {

    override suspend operator fun invoke() =
        eventRepository.getAllEventList()

}