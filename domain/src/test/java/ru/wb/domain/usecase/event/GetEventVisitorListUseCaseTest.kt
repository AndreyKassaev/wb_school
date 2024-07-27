package ru.wb.domain.usecase.event

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.EventVisitor

class GetEventVisitorListUseCaseTest {

    private val getEventVisitorListUseCase = mock<GetEventVisitorListUseCase>()

    @Test
    fun `event visitor list is not empty`() = runTest {

        Mockito.`when`(getEventVisitorListUseCase(eventId = any())).thenReturn(
            List(10){ EventVisitor.default }
        )

        assertFalse(getEventVisitorListUseCase(eventId = "1").isEmpty())

    }

}