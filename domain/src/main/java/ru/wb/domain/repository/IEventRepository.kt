package ru.wb.domain.repository

import ru.wb.domain.model.Event
import ru.wb.domain.model.EventVisitor

interface IEventRepository {

    suspend fun getAllEventList(): List<Event>

    suspend fun getEventById(eventId: String): Event

    suspend fun getEventVisitorList(eventId: String): List<EventVisitor>

    suspend fun addUserToEventVisitorList(eventId: String): List<EventVisitor>

    suspend fun removeUserFromEventVisitorList(eventId: String): List<EventVisitor>

    suspend fun getPersonalEventList(): List<Event>

}