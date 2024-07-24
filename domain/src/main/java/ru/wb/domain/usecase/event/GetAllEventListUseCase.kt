package ru.wb.domain.usecase.event

import ru.wb.domain.repository.IEventRepository

class GetAllEventListUseCase(
    private val eventRepository: IEventRepository
) {

    suspend operator fun invoke() =
        eventRepository.getAllEventList()

}