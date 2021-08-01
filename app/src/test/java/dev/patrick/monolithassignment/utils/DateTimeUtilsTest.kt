package dev.patrick.monolithassignment.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate

class DateTimeUtilsTest {

    @Test
    fun `Parse DateString to LocalDate returns correct LocalDate Object`() {

        // When
        val parsed = parseStringToDate("2021-08-01")

        // Then
        assertThat(parsed).isEqualTo(LocalDate.of(2021, 8, 1))
    }
}