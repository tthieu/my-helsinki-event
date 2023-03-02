package com.tthieu.myhelsinki.common.domain.model.event

import java.time.LocalDateTime

data class Event(
    val id: String,
    val name: String,
    val location: Location,
    val intro: String,
    val images: List<String>,
    val tags: List<String>,
    val dates: Dates
)
