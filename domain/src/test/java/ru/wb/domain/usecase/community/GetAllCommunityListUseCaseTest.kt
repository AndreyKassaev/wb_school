package ru.wb.domain.usecase.community

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import ru.wb.domain.model.Community

class GetAllCommunityListUseCaseTest {

    private val getAllCommunityListUseCase = mock<GetAllCommunityListUseCase>()

    @Test
    fun `community list is not empty`() = runBlocking{

        Mockito.`when`(getAllCommunityListUseCase()).thenReturn(
            List(10){ Community.default }
        )

        assertFalse(getAllCommunityListUseCase().isEmpty())

    }

}