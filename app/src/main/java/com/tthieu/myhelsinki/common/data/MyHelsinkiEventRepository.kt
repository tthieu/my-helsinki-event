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
import io.reactivex.Flowable
import retrofit2.HttpException
import javax.inject.Inject

class MyHelsinkiEventRepository @Inject constructor(
    private val api: MyHelsinkiApi,
    private val cache: Cache,
    private val apiEventMapper: ApiEventMapper,
    private val apiPaginationMapper: ApiPaginationMapper
): EventRepository {

    private val language = LanguageFilter.fi.toString()

    override fun getEvents(): Flowable<List<Event>> {
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
                apiPaginationMapper.mapToDomain(apiMeta, startItemToLoad, apiEvents?.size ?: 0)
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
}