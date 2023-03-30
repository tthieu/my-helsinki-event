package com.tthieu.myhelsinki.common.domain.model.event

data class Event(
    val id: String,
    val nameFi: String,
    val nameEn: String,
    val nameSv: String,
    val nameZh: String,
    val location: Location,
    val intro: String,
    val images: List<String>,
    val tags: List<String>,
    val dates: Dates,
)
