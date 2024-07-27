package ru.wb.domain.usecase.event

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.EventVisitor

class AcceptEventInvitationUseCaseTest {

    private val acceptEventInvitationUseCase = mock<AcceptEventInvitationUseCase>()

    @Test
    fun `event list not empty`(): Unit = runBlocking {

        Mockito.`when`(acceptEventInvitationUseCase(eventId = any())).thenReturn(
            List(3){ EventVisitor.default}
        )

        assertFalse(acceptEventInvitationUseCase(eventId = "1").isEmpty())

    }

}