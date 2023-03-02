package com.tthieu.myhelsinki.common.domain.model.event

data class Location(
    val lat: Double,
    val lon: Double,
    val address: Address
)
