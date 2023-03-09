package com.tthieu.myhelsinki.common.data.cache.model.cachedevent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class CachedTag(
    @PrimaryKey
    val tag: String
)
