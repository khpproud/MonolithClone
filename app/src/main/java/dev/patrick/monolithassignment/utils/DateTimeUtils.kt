package dev.patrick.monolithassignment.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

fun parseStringToDate(dateStr: String): LocalDate {
    return LocalDate.parse(dateStr)
}

//fun parseToDayOfWeek(date: LocalDate): String {
//    return when(date.dayOfWeek) {
//        DayOfWeek.SUNDAY -> "일"
//        DayOfWeek.MONDAY -> "월"
//        DayOfWeek.TUESDAY -> "화"
//        DayOfWeek.WEDNESDAY -> "수"
//        DayOfWeek.THURSDAY -> "목"
//        DayOfWeek.FRIDAY -> "금"
//        DayOfWeek.SATURDAY -> "토"
//    }
//}

fun parseKoreanDayOfWeek(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.SUNDAY -> "일"
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
    }
}

fun parseStringToTime(timeStr: String): LocalTime {
    return LocalTime.parse(timeStr)
}