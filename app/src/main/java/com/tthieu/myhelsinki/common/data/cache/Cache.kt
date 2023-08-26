package com.tthieu.myhelsinki.common.data.cache

import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEventAggregate
import kotlinx.coroutines.flow.Flow

interface Cache {
    fun getNearbyEvents(): Flow<List<CachedEventAggregate>>
    suspend fun storeNearbyEvents(events: List<CachedEventAggregate>)
    fun searchEventsBy(
        name: String,
        lang: String
    ): Flow<List<CachedEventAggregate>>
}