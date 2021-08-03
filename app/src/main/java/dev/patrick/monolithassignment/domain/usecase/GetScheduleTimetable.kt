package dev.patrick.monolithassignment.domain.usecase

import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.repository.IReserveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduleTimetable @Inject constructor(
    private val repository: IReserveRepository
) {
    suspend operator fun invoke(isSunday: Boolean): Flow<ProductItem> = repository.getScheduleTimetable(isSunday)
}