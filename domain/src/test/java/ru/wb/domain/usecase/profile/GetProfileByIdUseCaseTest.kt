package ru.wb.domain.usecase.profile

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.Profile

class GetProfileByIdUseCaseTest {

    private val getProfileByIdUseCase = mock<GetProfileByIdUseCase>()

    @Test
    fun `corrected type returned`(): Unit = runBlocking {

        Mockito.`when`(getProfileByIdUseCase(profileId = any())).thenReturn(
            Profile.default
        )

        val expected = Profile::class.java
        val actual = getProfileByIdUseCase(profileId = "1")

        assertInstanceOf(expected, actual)

    }


}