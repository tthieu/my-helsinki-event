package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.tthieu.myhelsinki.common.domain.model.event.Event

data class CachedEventAggregate(
    @Embedded
    val event: CachedEvent,
    @Relation(
        parentColumn = "eventId",
        entityColumn = "eventId"
    )
    val photos: List<CachedPhoto>,
    @Relation(
        parentColumn = "eventId",
        entityColumn = "tag",
        associateBy = Junction(CachedEventTagCrossRef::class)
    )
    val tags: List<CachedTag>
) {

    companion object {
        fun fromDomain(event: Event): CachedEventAggregate {
            return CachedEventAggregate(
                event = CachedEvent.fromDomain(event),
                photos = event.images.map {
                    CachedPhoto.fromDomain(event.id, it)
                },
                tags = event.tags.map { CachedTag(it) }
            )
        }
    }
}
