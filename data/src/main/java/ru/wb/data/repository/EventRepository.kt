package ru.wb.data.repository

import ru.wb.data.datasource.IDataSource
import ru.wb.domain.model.Event
import ru.wb.domain.model.EventVisitor
import ru.wb.domain.repository.IEventRepository

internal class EventRepository(
    private val dataSource: IDataSource
): IEventRepository {

    override suspend fun getAllEventList(): List<Event> =
        dataSource.getEventList()

    override suspend fun getEventById(eventId: String): Event =
        dataSource.getEventById(eventId = eventId)

    override suspend fun getPersonalEventList(): List<Event> =
        dataSource.getEventList()

    override suspend fun getEventVisitorList(eventId: String): List<EventVisitor> =
        dataSource.getEventVisitorList()

    override suspend fun addUserToEventVisitorList(eventId: String): List<EventVisitor> =
        dataSource.addUserToEventVisitorList(eventId = eventId)

    override suspend fun removeUserFromEventVisitorList(eventId: String): List<EventVisitor> =
        dataSource.removeUserFromEventVisitorList(eventId = eventId)

}