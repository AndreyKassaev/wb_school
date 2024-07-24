package ru.wb.domain.usecase.event

import ru.wb.domain.repository.IEventRepository

class GetPersonalEventListUseCase(
    private val eventRepository: IEventRepository
) {

    suspend operator fun invoke() =
        eventRepository.getPersonalEventList()

}