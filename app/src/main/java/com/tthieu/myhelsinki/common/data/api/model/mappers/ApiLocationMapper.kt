package com.tthieu.myhelsinki.common.data.api.model.mappers

import com.tthieu.myhelsinki.common.data.api.model.ApiEventLocation
import com.tthieu.myhelsinki.common.domain.model.event.Location
import javax.inject.Inject

class ApiLocationMapper @Inject constructor(
    private val apiAddressMapper: ApiAddressMapper
): ApiMapper<ApiEventLocation?, Location> {

    override fun mapToDomain(apiEntity: ApiEventLocation?): Location {
        return Location(
            lat = apiEntity?.lat ?: 0.0,
            lon = apiEntity?.lon ?: 0.0,
            address = apiAddressMapper.mapToDomain(apiEntity?.address)
        )
    }
}