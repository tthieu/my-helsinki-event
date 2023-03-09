package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = CachedEvent::class,
            parentColumns = ["eventId"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("eventId")]
)
data class CachedPhoto (
    @PrimaryKey(autoGenerate = true)
    val photoId: Long = 0,
    val eventId: String,
    val url: String
) {
    companion object {
        fun fromDomain(
            eventId: String,
            photoUrl: String
        ): CachedPhoto {
            return CachedPhoto(
                eventId = eventId,
                url = photoUrl
            )
        }
    }

    fun toDomain(): String = url
}