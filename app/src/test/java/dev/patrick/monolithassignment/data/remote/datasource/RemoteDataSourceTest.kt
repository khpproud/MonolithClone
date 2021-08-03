package dev.patrick.monolithassignment.data.remote.datasource

import com.google.common.truth.Truth.assertThat
import dev.patrick.monolithassignment.data.remote.api.ReserveApi
import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateDto
import dev.patrick.monolithassignment.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class RemoteDataSourceTest : CoroutineTestRule() {

    @Mock
    lateinit var api: ReserveApi

    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(api)
    }

    @Test
    fun `Get remote dates returns with Schedule date list`() = dispatcher.runBlockingTest {
        // Given
        whenever(api.getDates()) doReturn FakeRemote.getRemoteDates()

        // When
        val scheduleDates = remoteDataSource.getRemoteDates()

        // Then
        assertThat(scheduleDates).isEqualTo(
            listOf(
                ScheduleDateDto(date = "2021-08-01", true, false),
                ScheduleDateDto(date = "2021-08-02", true, false),
                ScheduleDateDto(date = "2021-08-03", false, false),
            )
        )
    }

    @Test
    fun `Get remote timetable returns with Timetable list`() = dispatcher.runBlockingTest {
        // Given
        whenever(api.getWeekDayTimetable()) doReturn FakeRemote.getRemoteTimetables()

        // When
        val timetables = remoteDataSource.getRemoteTimetables(isSunday = false)

        // Then
        assertThat(timetables.timeList.size).isEqualTo(1)
    }
}