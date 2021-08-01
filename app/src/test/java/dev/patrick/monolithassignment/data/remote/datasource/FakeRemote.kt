package dev.patrick.monolithassignment.data.remote.datasource

import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateDto
import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateResponse

object FakeRemote {

    suspend fun getRemoteDates(): ScheduleDateResponse {
        return ScheduleDateResponse(
            "AB123",
            "Success",
            listOf(
                ScheduleDateDto(date = "2021-08-01", true, false),
                ScheduleDateDto(date = "2021-08-02", true, false),
                ScheduleDateDto(date = "2021-08-03", false, false),
            )
        )
    }
}