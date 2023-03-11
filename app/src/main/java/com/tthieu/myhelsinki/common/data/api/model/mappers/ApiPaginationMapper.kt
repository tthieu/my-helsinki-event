package com.tthieu.myhelsinki.common.data.api.model.mappers

import com.tthieu.myhelsinki.common.data.api.model.ApiEventMeta
import com.tthieu.myhelsinki.common.domain.model.pagination.Pagination
import javax.inject.Inject

class ApiPaginationMapper @Inject constructor() {

    fun mapToDomain(
        apiEntity: ApiEventMeta?,
        currentIdx: Int,
        itemCount: Int
    ): Pagination {

        return Pagination(
            currentItemIdx = currentIdx + itemCount,
            totalItems = apiEntity?.count?.toIntOrNull() ?: 0
        )
    }
}