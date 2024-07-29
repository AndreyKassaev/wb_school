package ru.wb.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.wb.domain.model.Event

interface IEventRepository {

    fun getAllEventList(): Flow<List<Event>>

    fun getEventById(eventId: String): Flow<Event>

    fun addUserToEventVisitorList(eventId: String): Flow<Event>

    fun removeUserFromEventVisitorList(eventId: String): Flow<Event>

    fun getPersonalEventList(): Flow<List<Event>>

}