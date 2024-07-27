package ru.wb.domain.usecase.community

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.Community

class GetCommunityByIdUseCaseTest {

    private val getCommunityByIdUseCase = mock<GetCommunityByIdUseCase>()

    @Test
    fun `corrected type returned`(): Unit = runTest {

        Mockito.`when`(getCommunityByIdUseCase(communityId = any())).thenReturn(
            Community.default
        )

        val expected = Community::class.java
        val actual = getCommunityByIdUseCase(communityId = "1")

        assertInstanceOf(expected, actual)

    }

}