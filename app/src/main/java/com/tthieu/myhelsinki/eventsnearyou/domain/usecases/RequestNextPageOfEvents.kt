package com.tthieu.myhelsinki.eventsnearyou.domain.usecases

import com.tthieu.myhelsinki.common.domain.model.NoMoreEventsException
import com.tthieu.myhelsinki.common.domain.model.pagination.Pagination
import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.common.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestNextPageOfEvents @Inject constructor(
    private val eventRepository: EventRepository,
    private val dispatchersProvider: DispatchersProvider
) {
    suspend operator fun invoke(
        startItemToLoad: Int,
        numberOfItems: Int = Pagination.DEFAULT_PAGE_SIZE
    ): Pagination {

        return withContext(dispatchersProvider.io()) {
            val (events, pagination) = eventRepository.requestMoreEvents(startItemToLoad, numberOfItems)

            if (events.isEmpty()) {
                throw NoMoreEventsException("No events nearby :(")
            }

            eventRepository.storeEvents(events)

            return@withContext pagination
        }
    }
}