package com.tthieu.myhelsinki.eventsnearyou.presentation

sealed class EventsNearYouEvent {
    object RequestInitialEventsList: EventsNearYouEvent()
    object RequestMoreEvents: EventsNearYouEvent()
}
