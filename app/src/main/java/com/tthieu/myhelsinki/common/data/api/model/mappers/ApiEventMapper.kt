package com.tthieu.myhelsinki.common.data.api.model.mappers

import com.tthieu.myhelsinki.common.data.api.model.ApiEvent
import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.domain.model.event.Location
import javax.inject.Inject

class ApiEventMapper @Inject constructor (
    val apiLocationMapper: ApiLocationMapper,
    val apiDatesMapper: ApiDatesMapper
): ApiMapper<ApiEvent, Event> {

    override fun mapToDomain(apiEntity: ApiEvent): Event {
        return Event(
            id = apiEntity.id ?: throw MappingException("Event ID cannot be null"),
            name = apiEntity.name?.fi.orEmpty(),
            location = apiLocationMapper.mapToDomain(apiEntity.location),
            intro = apiEntity.description?.intro.orEmpty(),
            images = apiEntity.description?.images?.map { it?.url.orEmpty() }.orEmpty(),
            tags = apiEntity.tags?.map { it?.name.orEmpty() }.orEmpty(),
            dates = apiDatesMapper.mapToDomain(apiEntity.eventDates)
        )
    }

}