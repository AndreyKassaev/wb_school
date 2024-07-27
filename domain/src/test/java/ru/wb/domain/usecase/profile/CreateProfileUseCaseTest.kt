package ru.wb.domain.usecase.profile

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import ru.wb.domain.model.Profile

class CreateProfileUseCaseTest {

    private val createProfileUseCase = mock<CreateProfileUseCase>()

    @Test
    fun `corrected pin code must be validated`() = runBlocking{

        Mockito.`when`(createProfileUseCase(profile = any())).thenReturn(
            true
        )

        Assertions.assertEquals(true , createProfileUseCase(profile = Profile.default))
    }

}