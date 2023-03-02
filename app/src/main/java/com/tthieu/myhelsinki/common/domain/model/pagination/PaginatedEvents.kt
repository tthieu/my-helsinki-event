package com.tthieu.myhelsinki.common.domain.model.pagination

import com.tthieu.myhelsinki.common.domain.model.event.Event

data class PaginatedEvents(
    val events: List<Event>,
    val pagination: Pagination
)
