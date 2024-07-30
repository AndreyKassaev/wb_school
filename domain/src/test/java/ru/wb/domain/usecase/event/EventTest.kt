package ru.wb.domain.usecase.event

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import ru.wb.domain.repository.IEventRepository
import ru.wb.domain.stub.EventStub

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class EventTest {

    @Mock
    private lateinit var eventRepository: IEventRepository
    private lateinit var getAllEventListUseCase: GetAllEventListUseCase
    private lateinit var getEventByIdUseCase: GetEventByIdUseCase
    private lateinit var getPersonalEventListUseCase: GetPersonalEventListUseCase
    private lateinit var acceptEventInvitationUseCase: AcceptEventInvitationUseCase
    private lateinit var revokeEventInvitationUseCase: RevokeEventInvitationUseCase
    private val evenStub = EventStub()

    @BeforeEach
    fun setUp() {
        getAllEventListUseCase = GetAllEventListInteractor(eventRepository = eventRepository)
        getEventByIdUseCase = GetEventByIdInteractor(eventRepository = eventRepository)
        getPersonalEventListUseCase =
            GetPersonalEventListInteractor(eventRepository = eventRepository)
        acceptEventInvitationUseCase =
            AcceptEventInvitationInteractor(eventRepository = eventRepository)
        revokeEventInvitationUseCase =
            RevokeEventInvitationInteractor(eventRepository = eventRepository)
    }

    @Test
    fun `event list should be not empty`() =
        runTest {

            val expected = evenStub.getAllEventList()

            whenever(eventRepository.getAllEventList()).thenReturn(expected)

            val actual = getAllEventListUseCase()

            actual.collectLatest { eventList ->
                assert(eventList.isNotEmpty())
            }

        }

    @Test
    fun `received event should be with the same id as requested`() =
        runTest {

            val eventId = evenStub.eventIdStub

            val expected = evenStub.getEventById(eventId = eventId)

            whenever(eventRepository.getEventById(eventId = any())).thenReturn(expected)

            val actual = getEventByIdUseCase(eventId = eventId)

            actual.collectLatest { event ->
                assertEquals(
                    eventId,
                    event.id
                )
            }

        }

    @Test
    fun `personal event list should be not empty`() =
        runTest {

            val expected = evenStub.getPersonalEventList()

            whenever(eventRepository.getPersonalEventList()).thenReturn(expected)

            val actual = getPersonalEventListUseCase()

            actual.collectLatest { eventList ->
                assert(eventList.isNotEmpty())
            }
        }

    @Test
    fun `add user to event visitor list if invitation accepted`() =
        runTest {

            val profileId = evenStub.profileId
            val eventId = evenStub.eventIdStub
            val expected = evenStub.addUserToEventVisitorList(eventId = eventId)

            whenever(eventRepository.addUserToEventVisitorList(eventId = any())).thenReturn(expected)

            val actual = acceptEventInvitationUseCase(eventId = eventId)

            actual.collectLatest { event ->
                assert(
                    event.visitorList.any { eventVisitor ->
                        eventVisitor.visitorId == profileId
                    }
                )
            }

        }

    @Test
    fun `remove user from event visitor list if invitation revoked`() =
        runTest {

            val profileId = evenStub.profileId
            val eventId = evenStub.eventIdStub
            val expected = evenStub.removeUserFromEventVisitorList(eventId = eventId)

            whenever(eventRepository.removeUserFromEventVisitorList(eventId = any())).thenReturn(expected)

            val actual = revokeEventInvitationUseCase(eventId = eventId)

            actual.collectLatest { event ->
                assertFalse(
                    event.visitorList.any { eventVisitor ->
                        eventVisitor.visitorId == profileId
                    }
                )
            }

        }


}