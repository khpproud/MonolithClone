package dev.patrick.monolithassignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class ProductItem(
    val id: Long,
    val name: String,
    val displayName: String,
    val scheduleDate: LocalDate,
    val riderCount: Int,
    val timeList: List<TimeList>
) : Parcelable {
    @Parcelize
    data class TimeList(
        val timeSlot: LocalTime,
        val stcDetailId: String,
        val stockUseYn: Boolean,
        val enabled: Boolean,
        val stockStatusStr: StockStatus
    ) : Parcelable
}