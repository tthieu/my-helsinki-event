package com.tthieu.myhelsinki.common.domain.model.event

data class Address(
    val street: String,
    val postalCode: String,
    val locality: String,
    val neighborhood: String
)
