package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.tthieu.myhelsinki.common.domain.model.event.Address
import com.tthieu.myhelsinki.common.domain.model.event.Dates
import com.tthieu.myhelsinki.common.domain.model.event.Location
import java.time.LocalDateTime

@Entity(
    tableName = "events",
)
data class CachedEvent (
    @PrimaryKey
    val eventId: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val street: String,
    val postalCode: String,
    val locality: String,
    val neighborhood: String,
    val intro: String,
    val startingDate: String,
    val endingDate: String
)