package dev.patrick.monolithassignment.domain.repository

import dev.patrick.monolithassignment.domain.model.ScheduleDate

interface IReserveRepository {
    suspend fun getScheduleDate(): List<ScheduleDate>
}