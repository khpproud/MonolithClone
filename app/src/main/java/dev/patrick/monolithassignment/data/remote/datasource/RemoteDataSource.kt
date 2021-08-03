package dev.patrick.monolithassignment.data.remote.datasource

import dev.patrick.monolithassignment.data.remote.api.ReserveApi
import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateDto
import dev.patrick.monolithassignment.data.remote.dto.TimetableDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ReserveApi
) {

    suspend fun getRemoteDates(): List<ScheduleDateDto> {
        return api.getDates().data
    }

    suspend fun getRemoteTimetables(isSunday: Boolean): TimetableDto {
        return if (isSunday) api.getSundayTimetable().data else api.getWeekDayTimetable().data
    }
}