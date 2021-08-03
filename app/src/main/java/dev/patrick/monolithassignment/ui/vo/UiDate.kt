package dev.patrick.monolithassignment.ui.vo

import dev.patrick.monolithassignment.domain.model.ScheduleDate
import java.time.DayOfWeek

data class UiDate(
    val month: String,
    val date: String,
    val dayOfWeek: DayOfWeek,
    val enabled: Boolean,
    val selected: Boolean,
    val isHoliday: Boolean,
) {
    companion object {
        fun mapFromDomainDate(scheduleDate: ScheduleDate): UiDate {
            val month = "${scheduleDate.date.monthValue}월"
            val date = scheduleDate.date.dayOfMonth.toString()
            val dayOfWeek = scheduleDate.date.dayOfWeek

            return UiDate(month, date, dayOfWeek, scheduleDate.enabled, false, scheduleDate.holidayYn)
        }
    }
}