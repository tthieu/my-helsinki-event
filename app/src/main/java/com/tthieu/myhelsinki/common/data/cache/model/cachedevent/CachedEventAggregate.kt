package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

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
)
