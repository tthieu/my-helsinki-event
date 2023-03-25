package com.tthieu.myhelsinki.common.presentation.model.mappers

import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.presentation.model.UIEvent
import javax.inject.Inject

class UiEventMapper @Inject constructor(): UiMapper<Event, UIEvent> {
    override fun mapToView(input: Event): UIEvent {
        return UIEvent(
            id = input.id,
            title = input.name,
            photo = input.images.first()
        )
    }
}