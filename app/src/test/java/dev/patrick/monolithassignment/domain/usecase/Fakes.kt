package dev.patrick.monolithassignment.domain.usecase

import dev.patrick.monolithassignment.domain.model.ScheduleDate
import java.time.LocalDate

object Fakes {

    fun getScheduleDates(): List<ScheduleDate> {
        return listOf(
            ScheduleDate(date = LocalDate.of(2021, 8, 1), true, false),
            ScheduleDate(date = LocalDate.of(2021, 8, 2), false, false),
            ScheduleDate(date = LocalDate.of(2021, 8, 3), true, false),
        )
    }
}