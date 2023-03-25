package com.tthieu.myhelsinki.common.domain.repositories

import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.domain.model.pagination.PaginatedEvents
import io.reactivex.Flowable

interface EventRepository {

    fun getEvents(): Flowable<List<Event>>
    suspend fun requestMoreEvents(startItemToLoad: Int, numberOfItems: Int): PaginatedEvents
    suspend fun storeEvents(events: List<Event>)
}