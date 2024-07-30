package ru.wb.domain.usecase.auth

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import ru.wb.domain.repository.IAuthRepository
import ru.wb.domain.stub.AuthStub

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class AuthTest {

    @Mock
    private lateinit var authRepository: IAuthRepository
    private lateinit var requestPinCodeUseCase: RequestPinCodeUseCase
    private lateinit var validatePinCodeUseCase: ValidatePinCodeUseCase
    private val authStub = AuthStub()

    @BeforeEach
    fun setUp() {
        requestPinCodeUseCase = RequestPinCodeInteractor(authRepository = authRepository)
        validatePinCodeUseCase = ValidatePinCodeInteractor(authRepository = authRepository)
    }

    @Test
    fun `pin code should be not empty`() =
        runTest {

            val expected = authStub.requestPinCode()

            whenever(authRepository.requestPinCode()).thenReturn(expected)

            val actual = requestPinCodeUseCase()

            actual.collectLatest { pinCode ->
                assert(pinCode.isNotEmpty())
            }

        }

    @Test
    fun `pin code should be 4 digit`() =
        runTest {

            val pinCode = authStub.pinCode
            val expected = authStub.validatePinCode(pinCode = pinCode)
            whenever(authRepository.validatePinCode(pinCode = any())).thenReturn(expected)

            val actual = validatePinCodeUseCase(pinCode = pinCode)

            actual.collectLatest { isValid ->
                assertTrue(isValid)
            }

        }
}