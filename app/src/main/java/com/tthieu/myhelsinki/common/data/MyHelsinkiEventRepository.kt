package com.tthieu.myhelsinki.common.data

import com.tthieu.myhelsinki.common.data.api.MyHelsinkiApi
import com.tthieu.myhelsinki.common.data.api.model.mappers.ApiEventMapper
import com.tthieu.myhelsinki.common.data.api.model.mappers.ApiPaginationMapper
import com.tthieu.myhelsinki.common.data.cache.Cache
import com.tthieu.myhelsinki.common.data.cache.model.cachedevent.CachedEventAggregate
import com.tthieu.myhelsinki.common.domain.model.LanguageFilter
import com.tthieu.myhelsinki.common.domain.model.NetworkException
import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.domain.model.pagination.PaginatedEvents
import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.searchevent.domain.model.SearchParameters
import com.tthieu.myhelsinki.searchevent.domain.model.SearchResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class MyHelsinkiEventRepository @Inject constructor(
    private val api: MyHelsinkiApi,
    private val cache: Cache,
    private val apiEventMapper: ApiEventMapper,
    private val apiPaginationMapper: ApiPaginationMapper
): EventRepository {

    private val language = LanguageFilter.fi.toString()

    override fun getEvents(): Flow<List<Event>> {
        return cache.getNearbyEvents()
            .distinctUntilChanged() // ensure only events with new information get to the subscriber
            .map { eventList ->
                eventList.map {
                    it.event.toEventDomain(
                        it.photos,
                        it.tags
                    )
                }
            }
    }

    override suspend fun requestMoreEvents(
        startItemToLoad: Int,
        numberOfItems: Int
    ): PaginatedEvents {
        try {
            val (apiEvents, apiMeta) = api.fetchNearbyEvents(
                startItemToLoad,
                numberOfItems
            )

            return PaginatedEvents(
                apiEvents?.map {
                    apiEventMapper.mapToDomain(it)
                }.orEmpty(),
                apiPaginationMapper.mapToDomain(
                    apiMeta,
                    startItemToLoad,
                    apiEvents?.size ?: 0)
            )
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeEvents(events: List<Event>) {
        cache.storeNearbyEvents(
            events.map {
                CachedEventAggregate.fromDomain(it)
            }
        )
    }

    override fun getLanguages(): List<LanguageFilter> {
        return LanguageFilter.values().toList()
    }

    override suspend fun searchEventsRemotely(
        startItemToLoad: Int,
        searchParams: SearchParameters,
        numberOfItems: Int
    ): PaginatedEvents {

        val (apiEvents, apiMeta) = if (searchParams.lang.isNullOrEmpty()) {
            api.fetchNearbyEvents(
                startItemToLoad,
                numberOfItems
            )
        } else {
            api.searchEventBy(
                searchParams.lang,
                startItemToLoad,
                numberOfItems
            )
        }

        return PaginatedEvents(
            apiEvents?.map { apiEventMapper.mapToDomain(it) }.orEmpty(),
            apiPaginationMapper.mapToDomain(
                apiMeta,
                startItemToLoad,
                apiEvents?.size ?: 0)
        )
    }

    override fun searchCachedEventsBy(searchParameters: SearchParameters): Flow<SearchResults> {
        val (name, lang) = searchParameters

        return cache.searchEventsBy(name, lang)
            .distinctUntilChanged()
            .map { eventList ->
                eventList.map {
                    it.event.toEventDomain(
                        it.photos,
                        it.tags
                    )
                }
            }
            .map { SearchResults(it, searchParameters) }
    }
}