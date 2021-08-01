package dev.patrick.monolithassignment.domain.usecase

import dev.patrick.monolithassignment.domain.model.ScheduleDate
import dev.patrick.monolithassignment.domain.repository.IReserveRepository
import javax.inject.Inject

//typealias GetScheduleDateBaseUseCase = BaseUseCase<Unit?, List<ScheduleDate>>

class GetScheduleDate @Inject constructor(
    private val reserveRepository: IReserveRepository
) {
    suspend operator fun invoke(): List<ScheduleDate> = reserveRepository.getScheduleDate()
}