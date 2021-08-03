package dev.patrick.monolithassignment.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class DateTimeUtilsTest {

    @Test
    fun `Parse DateString to LocalDate returns correct LocalDate Object`() {

        // When
        val parsed = parseStringToDate("2021-08-01")

        // Then
        assertThat(parsed).isEqualTo(LocalDate.of(2021, 8, 1))
    }

    @Test
    fun `Parse TimeString to LocalTime returns correct LocalTime Object`() {

        // When
        val parsed = parseStringToTime("09:30")

        // Then
        assertThat(parsed).isEqualTo(LocalTime.of(9, 30))
    }
}