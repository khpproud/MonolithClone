package dev.patrick.monolithassignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ScheduleDate(
    val date: LocalDate,
    val enabled: Boolean,
    val holidayYn: Boolean
) : Parcelable