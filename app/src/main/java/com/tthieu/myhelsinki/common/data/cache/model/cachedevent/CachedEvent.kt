package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tthieu.myhelsinki.common.domain.model.event.Address
import com.tthieu.myhelsinki.common.domain.model.event.Dates
import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.domain.model.event.Location
import com.tthieu.myhelsinki.common.utils.DateTimeUtils

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
) {

    companion object {
        fun fromDomain(domainModel: Event): CachedEvent {

            val location = domainModel.location
            val address = domainModel.location.address
            val dates = domainModel.dates

            return CachedEvent(
                eventId = domainModel.id,
                name = domainModel.name,
                lat = location.lat,
                lon = location.lon,
                street = address.street,
                postalCode = address.postalCode,
                locality = address.locality,
                neighborhood = address.neighborhood,
                intro = domainModel.intro,
                startingDate = dates.startingDate.toString(),
                endingDate = dates.endingDate.toString()
            )
        }
    }

    fun toEventDomain(
        photos: List<CachedPhoto>,
        tags: List<CachedTag>
    ): Event {
        return Event(
            id = eventId,
            name = name,
            location = mapLocation(),
            intro = intro,
            images = photos.map { it.toDomain() },
            tags = tags.map { it.tag },
            dates = mapDates()
        )
    }

    private fun mapLocation(): Location {
        return Location(
            lat = lat,
            lon = lon,
            address = Address(street, postalCode, locality, neighborhood)
        )
    }

    private fun mapDates(): Dates {
        return Dates(
            startingDate = DateTimeUtils.parse(startingDate),
            endingDate = DateTimeUtils.parse(endingDate)
        )
    }
}