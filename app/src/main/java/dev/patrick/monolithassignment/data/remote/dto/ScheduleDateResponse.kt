package dev.patrick.monolithassignment.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleDateResponse(
    @field:Json(name = "code")
    val code: String?,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "data")
    val data: List<ScheduleDateDto>
)