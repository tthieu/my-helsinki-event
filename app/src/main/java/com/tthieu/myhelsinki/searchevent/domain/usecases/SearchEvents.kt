package com.tthieu.myhelsinki.searchevent.domain.usecases

import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.searchevent.domain.model.SearchParameters
import com.tthieu.myhelsinki.searchevent.domain.model.SearchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchEvents @Inject constructor(
    private val eventRepository: EventRepository
) {

    operator fun invoke(
        queryStateFlow: MutableStateFlow<String>,
        langStateFlow: MutableStateFlow<String>
    ): Flow<SearchResults> {
        val query = queryStateFlow
            .debounce(500L)
            .map { it.trim() }
            .filter { it.length >= 2 }

        val lang = langStateFlow.replaceUIEmptyValue()

        return combine(query, lang) { queryValue, langValue ->
            SearchParameters(queryValue, langValue)
        }
        .distinctUntilChanged()
        .flatMapLatest { parameters ->
            eventRepository.searchCachedEventsBy(parameters)
        }
        .flowOn(Dispatchers.Default)
    }

    private fun Flow<String>.replaceUIEmptyValue() = map {
        if (it == GetSearchFilters.NO_FILTER_SELECTED) "" else it
    }

}