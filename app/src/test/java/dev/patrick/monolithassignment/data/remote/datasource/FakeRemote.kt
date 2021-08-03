package dev.patrick.monolithassignment.data.remote.datasource

import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateDto
import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateResponse
import dev.patrick.monolithassignment.data.remote.dto.TimetableDto
import dev.patrick.monolithassignment.data.remote.dto.TimetableResponse

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

    suspend fun getRemoteTimetables(): TimetableResponse {
        return TimetableResponse(
            "AB123",
            "Success",
            TimetableDto(
                id = 123,
                name = "Test Name",
                displayName = "Test Display Name",
                scheduleDate = "2021-08-01",
                riderCount = 5,
                timeList = listOf(TimetableDto.TimeListDto(
                    timeSlot = "09:30",
                    stcDetailId = "1234",
                    stockUseYn = true,
                    enabled = true,
                    stockStatusStr = "여유"
                ))
            )
        )
    }
}