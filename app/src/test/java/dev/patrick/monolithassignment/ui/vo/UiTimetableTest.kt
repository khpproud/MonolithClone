package dev.patrick.monolithassignment.ui.vo

import com.google.common.truth.Truth.assertThat
import dev.patrick.monolithassignment.domain.model.ProductItem
import dev.patrick.monolithassignment.domain.model.StockStatus
import org.junit.Test
import java.time.LocalTime

class UiTimetableTest {

    @Test
    fun `map from Domain TimeList to UiTimetable returns correct result`() {
        // Given
        val correctUiTimetable = UiTimetable(
            timeSlot = "16:20",
            enabled = true,
            stockStatus = "여유"
        )
        val timeList = ProductItem.TimeList(
            timeSlot = LocalTime.of(16, 20),
            stcDetailId = "",
            stockUseYn = true,
            enabled = true,
            stockStatusStr = StockStatus.RELAXED
        )

        // When
        val uiTimetable = UiTimetable.mapFromDomainTimeList(timeList)

        // Then
        assertThat(uiTimetable).isEqualTo(correctUiTimetable)
    }
}