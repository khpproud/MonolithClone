package dev.patrick.monolithassignment.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimetableResponse(
    val code: String?,
    val message: String?,
    val data: TimetableDto
)