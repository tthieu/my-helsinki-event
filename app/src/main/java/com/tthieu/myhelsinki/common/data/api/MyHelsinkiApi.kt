package com.tthieu.myhelsinki.common.data.api

import com.tthieu.myhelsinki.common.data.api.model.ApiEvent
import com.tthieu.myhelsinki.common.data.api.model.ApiPaginatedEvents
import retrofit2.http.GET
import retrofit2.http.Query

interface MyHelsinkiApi {

    @GET(ApiConstants.EVENTS_ENDPOINT)
    suspend fun fetchNearbyEvents(
        @Query(ApiParameters.START) start: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int,
        // @Query(ApiParameters.LANGUAGE_FILTER) language: String,
        // @Query(ApiParameters.DISTANCE_FILTER) distance: String
    ): ApiPaginatedEvents

    @GET(ApiConstants.EVENTS_ENDPOINT)
    suspend fun searchEventBy(
        @Query(ApiParameters.LANGUAGE_FILTER) language: String,
        // @Query(ApiParameters.DISTANCE_FILTER) distance: String,
        @Query(ApiParameters.START) start: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int,
    ): ApiPaginatedEvents

    @GET(ApiConstants.EVENT_ENDPOINT)
    suspend fun fetchEvent(
        @Query(ApiParameters.ID) id: String,
        @Query(ApiParameters.LANGUAGE_FILTER) language: String,
    ): ApiEvent
}