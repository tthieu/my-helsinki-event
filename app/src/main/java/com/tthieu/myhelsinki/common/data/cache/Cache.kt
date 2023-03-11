package com.tthieu.myhelsinki.common.data.cache

import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEventAggregate
import io.reactivex.Flowable

interface Cache {
    fun getNearbyEvents(): Flowable<List<CachedEventAggregate>>
    suspend fun storeNearbyEvents(events: List<CachedEventAggregate>)
    // fun searchEventsBy(
    //
    // )
}