package com.tthieu.myhelsinki.common.data.api.model.mappers

import com.tthieu.myhelsinki.common.data.api.model.ApiEventDates
import com.tthieu.myhelsinki.common.domain.model.event.Dates
import com.tthieu.myhelsinki.common.utils.DateTimeUtils
import javax.inject.Inject

class ApiDatesMapper @Inject constructor():
    ApiMapper<ApiEventDates?, Dates> {

    override fun mapToDomain(apiEntity: ApiEventDates?): Dates {
        return Dates(
            startingDate = DateTimeUtils.parse(apiEntity?.startingDay.orEmpty()),
            endingDate = DateTimeUtils.parse(apiEntity?.endingDay.orEmpty())
        )
    }
}