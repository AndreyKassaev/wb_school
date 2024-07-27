package ru.wb.domain.usecase.event

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.Event

class GetEventByIdUseCaseTest {

    private val getEventByIdUseCase = mock<GetEventByIdUseCase>()

    @Test
    fun `corrected type returned`(): Unit = runBlocking {

        Mockito.`when`(getEventByIdUseCase(eventId = any())).thenReturn(
            Event.default
        )

        val expected = Event::class.java
        val actual = getEventByIdUseCase(eventId = "1")

        assertInstanceOf(expected, actual)

    }

}