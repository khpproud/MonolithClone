package dev.patrick.monolithassignment.ui.vo

import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.model.StockStatus
import java.time.format.DateTimeFormatter

data class UiTimetable(
    val timeSlot: String,
    val enabled: Boolean,
    val selected: Boolean,
    val stockStatus: StockStatus
) {
    companion object {
        fun mapFromDomainTimeList(timeList: ProductItem.TimeList): UiTimetable {
            return UiTimetable(
                timeSlot = timeList.timeSlot.format(DateTimeFormatter.ofPattern("HH:mm")),
                enabled = timeList.enabled,
                selected = false,
                stockStatus = timeList.stockStatus
            )
        }
    }
}