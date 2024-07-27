package ru.wb.domain.usecase.event

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.EventVisitor

class RevokeEventInvitationUseCaseTest {

    private val revokeEventInvitationUseCase = mock<RevokeEventInvitationUseCase>()

    @Test
    fun `event list not empty`(): Unit = runBlocking {

        Mockito.`when`(revokeEventInvitationUseCase(eventId = any())).thenReturn(
            List(3){ EventVisitor.default}
        )

        assertFalse(revokeEventInvitationUseCase(eventId = "1").isEmpty())

    }

}