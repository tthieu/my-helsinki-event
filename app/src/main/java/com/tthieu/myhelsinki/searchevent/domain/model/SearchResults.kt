package com.tthieu.myhelsinki.searchevent.domain.model

import com.tthieu.myhelsinki.common.domain.model.event.Event

data class SearchResults(
    val events: List<Event>,
    val searchParameters: SearchParameters
)
