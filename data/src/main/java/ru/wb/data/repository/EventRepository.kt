package ru.wb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.wb.data.datasource.IDataSource
import ru.wb.domain.model.Event
import ru.wb.domain.repository.IEventRepository

internal class EventRepository(
    private val dataSource: IDataSource
): IEventRepository {

    override fun getAllEventList(): Flow<List<Event>> =
        flow {
            emit(
                dataSource.getEventList()
            )
        }

    override fun getEventById(eventId: String): Flow<Event> =
        flow {
            emit(
                dataSource.getEventById(eventId = eventId)
            )
        }

    override fun getPersonalEventList(): Flow<List<Event>> =
        flow {
            emit(
                dataSource.getEventList()
            )
        }

    override fun addUserToEventVisitorList(eventId: String): Flow<Event> =
        flow {
            emit(
                dataSource.addUserToEventVisitorList(eventId = eventId)
            )
        }

    override fun removeUserFromEventVisitorList(eventId: String): Flow<Event> =
        flow {
            emit(
                dataSource.removeUserFromEventVisitorList(eventId = eventId)
            )
        }

}