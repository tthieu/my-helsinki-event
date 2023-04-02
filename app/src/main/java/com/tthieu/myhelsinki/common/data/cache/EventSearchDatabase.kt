package com.tthieu.myhelsinki.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tthieu.myhelsinki.common.data.cache.daos.EventsDao
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEvent
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEventTagCrossRef
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedPhoto
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedTag

@Database(
    entities = [
        CachedPhoto::class,
        CachedTag::class,
        CachedEvent::class,
        CachedEventTagCrossRef::class
    ],
    version = 2
)
abstract class EventSearchDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
}