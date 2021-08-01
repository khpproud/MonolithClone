package dev.patrick.monolithassignment.data.remote.api

import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateResponse
import retrofit2.http.GET

interface ReserveApi {

    @GET("date")
    suspend fun getDates(): ScheduleDateResponse

    // basic, sunday...
}