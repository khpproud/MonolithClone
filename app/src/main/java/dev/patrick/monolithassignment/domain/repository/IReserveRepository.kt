package dev.patrick.monolithassignment.domain.repository

import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.model.ScheduleDate
import kotlinx.coroutines.flow.Flow

interface IReserveRepository {
    suspend fun getScheduleDate(): List<ScheduleDate>

    fun getScheduleTimetable(isSunday: Boolean): Flow<ProductItem>
}