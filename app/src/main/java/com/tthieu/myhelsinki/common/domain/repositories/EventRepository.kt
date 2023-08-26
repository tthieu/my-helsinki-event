package com.tthieu.myhelsinki.common.domain.repositories

import com.tthieu.myhelsinki.common.domain.model.LanguageFilter
import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.domain.model.pagination.PaginatedEvents
import com.tthieu.myhelsinki.searchevent.domain.model.SearchParameters
import com.tthieu.myhelsinki.searchevent.domain.model.SearchResults
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    fun getEvents(): Flow<List<Event>>
    suspend fun requestMoreEvents(startItemToLoad: Int, numberOfItems: Int): PaginatedEvents
    suspend fun storeEvents(events: List<Event>)
    fun getLanguages(): List<LanguageFilter>

    suspend fun searchEventsRemotely(
        startItemToLoad: Int,
        searchParams: SearchParameters,
        numberOfItems: Int
    ): PaginatedEvents

    fun searchCachedEventsBy(searchParameters: SearchParameters): Flow<SearchResults>
}