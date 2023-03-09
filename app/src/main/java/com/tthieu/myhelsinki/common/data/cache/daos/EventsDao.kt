package com.tthieu.myhelsinki.common.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEvent
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEventAggregate
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedPhoto
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedTag
import io.reactivex.Flowable

@Dao
abstract class EventsDao {
    @Transaction
    @Query("SELECT * FROM events")
    abstract fun getAllEvents(): Flowable<List<CachedEventAggregate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEventAggregate(
        event: CachedEvent,
        photos: List<CachedPhoto>,
        tags: List<CachedTag>
    )

    suspend fun insertEvents(eventAggregates: List<CachedEventAggregate>) {
        for (eventAggregate in eventAggregates) {
            insertEventAggregate(
                eventAggregate.event,
                eventAggregate.photos,
                eventAggregate.tags
            )
        }
    }
}