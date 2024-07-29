package ru.wb.domain.usecase.profile

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import ru.wb.domain.repository.IProfileRepository
import ru.wb.domain.stub.ProfileStub

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class ProfileTest {

    @Mock
    private lateinit var profileRepository: IProfileRepository
    private lateinit var getProfileByIdUseCase: GetProfileByIdUseCase
    private lateinit var createProfileUseCase: CreateProfileUseCase
    private val profileStub = ProfileStub()

    @BeforeEach
    fun setUp() {
        getProfileByIdUseCase = GetProfileByIdInteractor(profileRepository = profileRepository)
        createProfileUseCase = CreateProfileInteractor(profileRepository = profileRepository)
    }

    @Test
    fun `received profile should be with the same id as requested`() =
        runTest {

            val profileId = profileStub.profile.id

            val expected = profileStub.getProfileById(profileId = profileId)

            whenever(profileRepository.getProfileById(profileId = any())).thenReturn(expected)

            val actual = getProfileByIdUseCase(profileId = profileId)

            actual.collectLatest { profile ->
                assertEquals(
                    profileId,
                    profile.id
                )
            }

        }

    @Test
    fun `create profile should return true`() =
        runTest {

            val profile = profileStub.profile

            val expected = profileStub.createProfile(profile = profile)

            whenever(profileRepository.createProfile(profile = any())).thenReturn(expected)

            val actual = createProfileUseCase(profile = profile)

            actual.collectLatest { result ->
                assert(result)
            }

        }


}