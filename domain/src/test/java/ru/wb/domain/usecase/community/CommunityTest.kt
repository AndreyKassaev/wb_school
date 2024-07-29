package ru.wb.domain.usecase.community

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
import ru.wb.domain.repository.ICommunityRepository
import ru.wb.domain.stub.CommunityStub

@ExtendWith(MockitoExtension::class)
@ExperimentalCoroutinesApi
class CommunityTest {

    @Mock
    private lateinit var communityRepository: ICommunityRepository
    private lateinit var getAllCommunityListUseCase: GetAllCommunityListUseCase
    private lateinit var getCommunityByIdUseCase: GetCommunityByIdUseCase
    private val communityStub = CommunityStub()

    @BeforeEach
    fun setUp() {
        getAllCommunityListUseCase =
            GetAllCommunityListInteractor(communityRepository = communityRepository)
        getCommunityByIdUseCase =
            GetCommunityByIdInteractor(communityRepository = communityRepository)
    }

    @Test
    fun `community list should be not empty`() =
        runTest {

            val expected = communityStub.getAllCommunityList()

            whenever(communityRepository.getAllCommunityList()).thenReturn(expected)

            val actual = getAllCommunityListUseCase()

            actual.collectLatest { communityList ->
                assert(communityList.isNotEmpty())
            }

        }

    @Test
    fun `received community should be with the same id as requested`() =
        runTest {

            val communityId = communityStub.communityId
            val expected = communityStub.getCommunityById(communityId = communityId)

            whenever(communityRepository.getCommunityById(communityId = any())).thenReturn(expected)

            val actual = getCommunityByIdUseCase(communityId = communityId)

            actual.collectLatest { community ->
                assertEquals(
                    communityId,
                    community.id
                )
            }

        }

}