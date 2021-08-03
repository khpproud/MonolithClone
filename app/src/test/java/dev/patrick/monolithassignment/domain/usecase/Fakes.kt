package dev.patrick.monolithassignment.domain.usecase

import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.model.ScheduleDate
import dev.patrick.monolithassignment.domain.model.StockStatus
import java.time.LocalDate
import java.time.LocalTime

object Fakes {

    fun getScheduleDates(): List<ScheduleDate> {
        return listOf(
            ScheduleDate(date = LocalDate.of(2021, 8, 1), true, false),
            ScheduleDate(date = LocalDate.of(2021, 8, 2), false, false),
            ScheduleDate(date = LocalDate.of(2021, 8, 3), true, false),
        )
    }

    fun getScheduleTimetableWeekDays(): ProductItem {
        return getProductItem(relaxedTimeList)
    }


    fun getProductItem(timeList: List<ProductItem.TimeList>): ProductItem {
        return ProductItem(
            id = 1L,
            name = "Test Schedule",
            displayName = "Test Test Test",
            scheduleDate = LocalDate.of(2021, 8, 1),
            riderCount = 5,
            timeList = timeList
        )
    }

    val relaxedTimeList = listOf<ProductItem.TimeList>(
        ProductItem.TimeList(
            timeSlot = LocalTime.of(9, 10),
            stcDetailId = "123",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.RELAXED
        ),
        ProductItem.TimeList(
            timeSlot = LocalTime.of(10, 10),
            stcDetailId = "123",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.RELAXED
        ),
    )

    val crowdedTimeList = listOf<ProductItem.TimeList>(
        ProductItem.TimeList(
            timeSlot = LocalTime.of(9, 10),
            stcDetailId = "123",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.CROWDED
        ),
        ProductItem.TimeList(
            timeSlot = LocalTime.of(10, 10),
            stcDetailId = "123",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.CROWDED
        ),
    )

    val soldOutTimeList = listOf<ProductItem.TimeList>(
        ProductItem.TimeList(
            timeSlot = LocalTime.of(9, 10),
            stcDetailId = "123",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.SOLDOUT
        ),
        ProductItem.TimeList(
            timeSlot = LocalTime.of(10, 10),
            stcDetailId = "123",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.SOLDOUT
        ),
    )
}