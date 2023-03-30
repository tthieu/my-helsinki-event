package com.tthieu.myhelsinki.searchevent.domain.usecases

import com.tthieu.myhelsinki.common.domain.model.NoMoreEventsException
import com.tthieu.myhelsinki.common.domain.model.pagination.Pagination
import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.common.utils.DispatchersProvider
import com.tthieu.myhelsinki.searchevent.domain.model.SearchParameters
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchEventsRemotely @Inject constructor(
    private val eventRepository: EventRepository,
    private val dispatchersProvider: DispatchersProvider
) {

    suspend operator fun invoke(
        startItemToLoad: Int,
        searchParams: SearchParameters,
        numberOfItems: Int
    ): Pagination {
        return withContext(dispatchersProvider.io()) {
            val (events, pagination) = eventRepository.searchEventsRemotely(
                startItemToLoad, searchParams, numberOfItems
            )

            if (events.isEmpty()) {
                throw NoMoreEventsException("Couldn't find more events that match the search parameters")
            }

            eventRepository.storeEvents(events)

            return@withContext pagination
        }
    }
}