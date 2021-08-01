package dev.patrick.monolithassignment.ui.reserve

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import dev.patrick.monolithassignment.domain.model.ScheduleDate
import dev.patrick.monolithassignment.domain.usecase.GetScheduleDate
import dev.patrick.monolithassignment.ui.vo.UiDate
import dev.patrick.monolithassignment.util.CoroutineTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReserveViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var getScheduleDate: GetScheduleDate

    @Mock
    lateinit var observer: Observer<List<UiDate>>

    lateinit var viewModel: ReserveViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ReserveViewModel(getScheduleDate)
        viewModel.uiDates.observeForever(observer)
    }

    @After
    fun teardown() {
        viewModel.uiDates.removeObserver(observer)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get UiDates return with UiDate list object`() = testDispatcher.runBlockingTest {
        // Given
        whenever(getScheduleDate()) doReturn listOf(
            ScheduleDate(LocalDate.of(2021, 8, 1), true, false),
            ScheduleDate(LocalDate.of(2021,8, 2), true, false)
        )

        // When
        viewModel.getUiDates()

        // Then
        verify(observer).onChanged(listOf(
            UiDate("8월", "1", "일", true, false),
            UiDate("8월", "2", "월", true, false)
        ))
    }
}