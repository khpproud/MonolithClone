package dev.patrick.monolithassignment.data.remote.api

import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateResponse
import dev.patrick.monolithassignment.data.remote.dto.TimetableResponse
import retrofit2.http.GET

interface ReserveApi {

    @GET("date")
    suspend fun getDates(): ScheduleDateResponse

    @GET("basic")
    suspend fun getWeekDayTimetable(): TimetableResponse

    @GET("sunday")
    suspend fun getSundayTimetable(): TimetableResponse

}