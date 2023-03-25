package com.tthieu.myhelsinki.eventsnearyou.domain.usecases

import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import javax.inject.Inject

class GetEvents @Inject constructor(
    private val eventRepository: EventRepository
) {

    operator fun invoke() = eventRepository.getEvents()
        .filter { it.isNotEmpty() }
}