package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["eventId", "tag"], indices = [Index("tag")])
data class CachedEventTagCrossRef(
    val eventId: String,
    val tag: String
)
