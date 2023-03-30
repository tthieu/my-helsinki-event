package com.tthieu.myhelsinki.searchevent.presentation

import com.tthieu.myhelsinki.common.presentation.Event
import com.tthieu.myhelsinki.common.presentation.model.UIEvent

data class SearchViewState(
    val noSearchQuery: Boolean = true,
    val searchResults: List<UIEvent> = emptyList(),
    val langFilterValues: Event<List<String>> = Event(emptyList()),
    val searchingRemotely: Boolean = false,
    val noRemoteResults: Boolean = false,
    val failure: Event<Throwable>? = null
) {
    fun updateToReadyToSearch(langs: List<String>): SearchViewState {
        return copy(
            langFilterValues = Event(langs)
        )
    }

    fun updateToNoSearchQuery(): SearchViewState {
        return copy(
            noSearchQuery = true,
            searchResults = emptyList(),
            noRemoteResults = false
        )
    }

    fun updateToSearching(): SearchViewState {
        return copy(
            noSearchQuery = false,
            searchingRemotely = false,
            noRemoteResults = false
        )
    }

    fun updateToSearchingRemotely(): SearchViewState {
        return copy(
            searchingRemotely = true,
            searchResults = emptyList()
        )
    }

    fun updateToHasSearchResults(events: List<UIEvent>): SearchViewState {
        return copy(
            noSearchQuery = false,
            searchResults = events,
            searchingRemotely = false,
            noRemoteResults = false
        )
    }

    fun updateToNoResultsAvailable(): SearchViewState {
        return copy(
            searchingRemotely = false,
            noRemoteResults = true
        )
    }

    fun updateToHasFailure(throwable: Throwable): SearchViewState {
        return copy(
            failure = Event(throwable)
        )
    }
}
