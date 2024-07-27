package ru.wb.domain.usecase.event

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import ru.wb.domain.model.Event

class GetPersonalEventListUseCaseTest {

    private val getPersonalEventListUseCase = mock<GetPersonalEventListUseCase>()

    @Test
    fun `personal event list is not empty`() = runTest {

        Mockito.`when`(getPersonalEventListUseCase()).thenReturn(
            List(10){ Event.default }
        )

        assertFalse(getPersonalEventListUseCase().isEmpty())

    }

}