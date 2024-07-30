package ru.wb.domain.stub

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.wb.domain.model.Event
import ru.wb.domain.model.EventVisitor

class EventStub {

    val profileId = "1"
    val eventIdStub = "1"

    fun getAllEventList(): Flow<List<Event>> =
        flowOf(
            List(10) { Event.default }
        )


    fun getEventById(eventId: String): Flow<Event> =
        flowOf(
            Event.default.copy(id = eventIdStub)
        )


    fun getPersonalEventList(): Flow<List<Event>> =
        flowOf(
            List(10) { Event.default }
        )


    fun addUserToEventVisitorList(eventId: String): Flow<Event> =
        flowOf(
            Event.default.copy(
                visitorList = listOf(
                    EventVisitor.default.copy(
                        visitorId = profileId
                    )
                )
            )
        )


    fun removeUserFromEventVisitorList(eventId: String): Flow<Event> =
        flowOf(
            Event.default.copy(
                visitorList = listOf(
                    EventVisitor.default
                )
            )
        )

}