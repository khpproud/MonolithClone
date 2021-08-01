package dev.patrick.monolithassignment.data.remote.datasource

import dev.patrick.monolithassignment.data.remote.api.ReserveApi
import dev.patrick.monolithassignment.data.remote.dto.ScheduleDateDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ReserveApi
) {

    suspend fun getRemoteDates(): List<ScheduleDateDto> {
        return api.getDates().data
    }
}