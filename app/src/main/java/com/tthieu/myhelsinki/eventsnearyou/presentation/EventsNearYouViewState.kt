package com.tthieu.myhelsinki.eventsnearyou.presentation

import com.tthieu.myhelsinki.common.presentation.Event
import com.tthieu.myhelsinki.common.presentation.model.UIEvent

data class EventsNearYouViewState(
    val loading: Boolean = true,
    val events: List<UIEvent> = emptyList(),
    val noMoreEventsNearby: Boolean = false,
    val failure: Event<Throwable>? = null
)
