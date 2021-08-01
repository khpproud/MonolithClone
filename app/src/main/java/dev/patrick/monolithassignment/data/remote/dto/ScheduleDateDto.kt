package dev.patrick.monolithassignment.data.remote.dto

import com.squareup.moshi.JsonClass
import dev.patrick.monolithassignment.domain.model.ScheduleDate
import dev.patrick.monolithassignment.utils.parseStringToDate

@JsonClass(generateAdapter = true)
data class ScheduleDateDto(
    val date: String,
    val enabled: Boolean,
    val holidayYn: Boolean
) {
    fun mapToDomain(): ScheduleDate = ScheduleDate(
        date = parseStringToDate(date),
        enabled = enabled,
        holidayYn = holidayYn
    )
}