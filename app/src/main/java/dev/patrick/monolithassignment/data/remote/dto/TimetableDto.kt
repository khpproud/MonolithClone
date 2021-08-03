package dev.patrick.monolithassignment.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.model.StockStatus
import dev.patrick.monolithassignment.utils.parseStringToDate
import dev.patrick.monolithassignment.utils.parseStringToTime

@JsonClass(generateAdapter = true)
data class TimetableDto(
    @field:Json(name = "prdId")
    val id: Long,
    @field:Json(name = "productName")
    val name: String,
    @field:Json(name = "productDisplayName")
    val displayName: String,
    @field:Json(name = "reserveDt")
    val scheduleDate: String,
    @field:Json(name = "riderCount")
    val riderCount: Int,
    @field:Json(name = "timeList")
    val timeList: List<TimeListDto>
) {

    @JsonClass(generateAdapter = true)
    data class TimeListDto(
        @field:Json(name = "timeSlot")
        val timeSlot: String,
        @field:Json(name = "stcDetailId")
        val stcDetailId: String,
        @field:Json(name = "stockUseYn")
        val stockUseYn: Boolean,
        @field:Json(name = "enabled")
        val enabled: Boolean,
        @field:Json(name = "stockStatusStr")
        val stockStatusStr: String
    ) {
        fun mapToDomain(): ProductItem.TimeList {
            return ProductItem.TimeList(
                timeSlot = parseStringToTime(timeSlot),
                stcDetailId = stcDetailId,
                stockUseYn = stockUseYn,
                enabled = enabled,
                stockStatus = StockStatus.parseStockStatus(stockStatusStr)
            )
        }
    }

    fun mapToDomain(): ProductItem {
        return ProductItem(
            id = id,
            name = name,
            displayName = displayName,
            riderCount = riderCount,
            scheduleDate = parseStringToDate(scheduleDate),
            timeList = timeList.map { it.mapToDomain() }
        )
    }
}