package com.tthieu.myhelsinki.searchevent.domain.usecases

import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.searchevent.domain.model.SearchParameters
import com.tthieu.myhelsinki.searchevent.domain.model.SearchResults
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchEvents @Inject constructor(
    private val eventRepository: EventRepository
) {

    operator fun invoke(
        querySubject: BehaviorSubject<String>,
        langSubject: BehaviorSubject<String>
    ): Flowable<SearchResults> {
        val query = querySubject
            .debounce(500L, TimeUnit.MILLISECONDS)
            .map { it.trim() }
            .filter { it.length >= 2}

        val lang = langSubject.replaceUIEmptyValue()

        return Observable.combineLatest(query, lang, combiningFunction)
            .toFlowable(BackpressureStrategy.LATEST)
            .switchMap { parameters: SearchParameters ->
                eventRepository.searchCachedEventsBy(parameters)
            }
    }

    private fun BehaviorSubject<String>.replaceUIEmptyValue() = map {
        if (it == GetSearchFilters.NO_FILTER_SELECTED) "" else it
    }

    private val combiningFunction: BiFunction<String, String, SearchParameters>
        get() = BiFunction { query, lang ->
            SearchParameters(query, lang)
        }
}