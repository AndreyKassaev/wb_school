package ru.wb.domain.usecase.community

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.Event

class GetCommunityEventListUseCaseTest {

    private val getCommunityEventListUseCase = mock<GetCommunityEventListUseCase>()

    @Test
    fun `corrected type returned`(): Unit = runBlocking {

        Mockito.`when`(getCommunityEventListUseCase(communityId = any())).thenReturn(
            List(3){Event.default}
        )

        val expected = Event::class.java
        val actual = getCommunityEventListUseCase(communityId = "1")?.first()

        assertInstanceOf(expected, actual)

    }

}