package ru.wb.domain.usecase.auth

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.doAnswer
import kotlin.random.Random

class ValidatePinCodeUseCaseTest {

    private val requestPinCodeUseCase = mock<RequestPinCodeUseCase>()
    private val validatePinCodeUseCase = mock<ValidatePinCodeUseCase>()

    @Test
    fun `corrected pin code must be validated`() = runBlocking{

        Mockito.`when`(requestPinCodeUseCase()).thenReturn(
            Random.nextInt(from = 1000, until = 9999).toString()
        )

        val pinCode = requestPinCodeUseCase()
        Mockito.`when`(validatePinCodeUseCase(pinCode = pinCode)).doAnswer {
            it.getArgument<String>(0) == pinCode
        }

        Assertions.assertEquals(true , validatePinCodeUseCase(pinCode = pinCode))
    }

}