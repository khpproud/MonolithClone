package dev.patrick.monolithassignment.domain.usecase

import com.google.common.truth.Truth.assertThat
import dev.patrick.monolithassignment.data.repository.ReserveRepository
import dev.patrick.monolithassignment.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetScheduleTimetableTest : CoroutineTestRule() {

    @Mock
    lateinit var reserveRepository: ReserveRepository

    lateinit var useCase: GetScheduleTimetable

    @Before
    fun setup() {
        useCase = GetScheduleTimetable(reserveRepository)
    }

    @Test
    fun `Get ScheduleTimetable returns weekDay's schedule`() = dispatcher.runBlockingTest {
        // Given
        whenever(reserveRepository.getScheduleTimetable(isSunday = false)) doReturn flowOf(Fakes.getScheduleTimetableWeekDays())

        // When
        val productItem = useCase(isSunday = false)

        // Then
        assertThat(productItem.first().timeList.size).isEqualTo(2)
    }

    // TODO: Check for sunday's results correct
    @Test
    fun `Get ScheduleTimetable returns Sunday's schedule`() = dispatcher.runBlockingTest {
        // Given

        // When

        // Then
    }
}