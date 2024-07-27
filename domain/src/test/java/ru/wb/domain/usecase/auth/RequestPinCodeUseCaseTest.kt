package ru.wb.domain.usecase.auth

import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.matchesPattern
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.random.Random

class RequestPinCodeUseCaseTest {

    private val requestPinCodeUseCase = mock<RequestPinCodeUseCase>()

    @Test
    fun `pin code must be 4 digit number`() = runTest {

        Mockito.`when`(requestPinCodeUseCase()).thenReturn(
            Random.nextInt(from = 1000, until = 9999).toString()
        )

        assertThat("1235", matchesPattern("""\d{4}"""))
    }

}