package dev.patrick.monolithassignment.domain.usecase

import com.google.common.truth.Truth.assertThat
import dev.patrick.monolithassignment.domain.repository.IReserveRepository
import dev.patrick.monolithassignment.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetScheduleDateTest : CoroutineTestRule() {

    @Mock
    lateinit var reserveRepository: IReserveRepository

    lateinit var useCase: GetScheduleDate

    @Before
    fun setup() {
        useCase = GetScheduleDate(reserveRepository)
    }

    @Test
    fun `Get ScheduleDate returns correct result with schedule date list`() = dispatcher.runBlockingTest {
        // Given
        whenever(reserveRepository.getScheduleDate()) doReturn Fakes.getScheduleDates()

        // When
        val scheduleDates = useCase()

        // Then
        assertThat(scheduleDates.size).isEqualTo(3)
        verify(reserveRepository).getScheduleDate()
    }
}