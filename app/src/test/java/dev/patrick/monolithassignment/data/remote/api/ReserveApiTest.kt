package dev.patrick.monolithassignment.data.remote.api

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.patrick.monolithassignment.data.remote.interceptor.MockInterceptor
import dev.patrick.monolithassignment.util.CoroutineTestRule
import dev.patrick.monolithassignment.utils.readFileFromAssets
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ReserveApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    lateinit var context: Context

    lateinit var api: ReserveApi

    lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        context = ApplicationProvider.getApplicationContext()
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .build()
            .create(ReserveApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

//    @Test
//    fun `Get basic json file returns parsed strings`() = coroutinesTestRule.dispatcher.runBlockingTest {
//        enqueueResponse("basic.json")
//        val weekdayTimetable = api.getSundayTimetable().data
//
//        assertThat(weekdayTimetable).isNotNull()
//        assertThat(weekdayTimetable.timeList.size).isGreaterThan(1)
//    }
//
//    private fun enqueueResponse(filename: String) {
//        val mockResponse = MockResponse()
//        val timetable = context.readFileFromAssets(filename)
//
//        mockWebServer.enqueue(
//            mockResponse.setBody(timetable)
//        )
//    }
}