package com.tthieu.myhelsinki.common.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPaginatedEvents(
    @Json(name = "data")
    val events: List<ApiEvent>?,
    @Json(name = "meta")
    val meta: ApiEventMeta?,
    // @Json(name = "tags")
    // val tags: ApiEventTags?
)

@JsonClass(generateAdapter = true)
data class ApiEventMeta(
    @Json(name = "count")
    val count: String?,
    @Json(name = "next")
    val next: String?
)

