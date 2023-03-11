package com.tthieu.myhelsinki.common.data.cache

import com.tthieu.myhelsinki.common.data.cache.daos.EventsDao
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEventAggregate
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val eventsDao: EventsDao
) : Cache {
    override fun getNearbyEvents(): Flowable<List<CachedEventAggregate>> {
        return eventsDao.getAllEvents()
    }

    override suspend fun storeNearbyEvents(events: List<CachedEventAggregate>) {
        eventsDao.insertEvents(events)
    }
}