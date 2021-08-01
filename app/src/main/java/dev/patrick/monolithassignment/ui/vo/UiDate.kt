package dev.patrick.monolithassignment.ui.vo

import dev.patrick.monolithassignment.domain.model.ScheduleDate
import dev.patrick.monolithassignment.utils.parseToDayOfWeek

data class UiDate(
    val month: String,
    val date: String,
    val dayOfWeek: String,
    val enabled: Boolean,
    val isHoliday: Boolean,
) {
    companion object {
        fun mapFromDomainDate(scheduleDate: ScheduleDate): UiDate {
            val month = "${scheduleDate.date.monthValue}월"
            val date = scheduleDate.date.dayOfMonth.toString()
            val dayOfWeek = parseToDayOfWeek(scheduleDate.date)

            return UiDate(month, date, dayOfWeek, scheduleDate.enabled, scheduleDate.holidayYn)
        }
    }
}