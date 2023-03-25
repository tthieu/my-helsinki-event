package com.tthieu.myhelsinki.common.presentation.model.mappers

import com.google.type.DateTime
import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.presentation.model.UIEvent
import com.tthieu.myhelsinki.common.utils.DateTimeUtils
import java.time.LocalDateTime
import javax.inject.Inject

class UiEventMapper @Inject constructor(): UiMapper<Event, UIEvent> {
    override fun mapToView(input: Event): UIEvent {
        return UIEvent(
            id = input.id,
            title = input.name,
            photo = input.images.first(),
            date = input.dates.startingDate?.let { mapStartingDate(it) } ?: ""
        )
    }

    private fun mapStartingDate(dateTime: LocalDateTime): String {
        return DateTimeUtils.toString(dateTime, "yyyy-MM-dd HH:mm:ss")
    }
}