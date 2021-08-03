package dev.patrick.monolithassignment.data.repository

import dev.patrick.monolithassignment.data.remote.datasource.RemoteDataSource
import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.model.ScheduleDate
import dev.patrick.monolithassignment.domain.repository.IReserveRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReserveRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IReserveRepository {

    override suspend fun getScheduleDate(): List<ScheduleDate> {
        return remoteDataSource.getRemoteDates().map { it.mapToDomain() }
    }

    override fun getScheduleTimetable(isSunday: Boolean): Flow<ProductItem> = flow {
        emit(remoteDataSource.getRemoteTimetables(isSunday).mapToDomain())
    }
}