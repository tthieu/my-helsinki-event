package com.tthieu.myhelsinki.common.data.api.model.mappers

import com.tthieu.myhelsinki.common.data.api.model.ApiEventAddress
import com.tthieu.myhelsinki.common.domain.model.event.Address
import javax.inject.Inject

class ApiAddressMapper @Inject constructor(): ApiMapper<ApiEventAddress?, Address> {

    override fun mapToDomain(apiEntity: ApiEventAddress?): Address {
        return Address(
            street = apiEntity?.streetAddress.orEmpty(),
            postalCode = apiEntity?.postalCode.orEmpty(),
            locality = apiEntity?.locality.orEmpty(),
            neighborhood = apiEntity?.neighbourhood.orEmpty()
        )
    }
}