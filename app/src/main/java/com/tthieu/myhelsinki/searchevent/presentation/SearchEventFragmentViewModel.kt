package com.tthieu.myhelsinki.searchevent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tthieu.myhelsinki.common.domain.model.NoMoreEventsException
import com.tthieu.myhelsinki.common.domain.model.event.Event
import com.tthieu.myhelsinki.common.domain.model.pagination.Pagination
import com.tthieu.myhelsinki.common.presentation.model.mappers.UiEventMapper
import com.tthieu.myhelsinki.common.utils.createExceptionHandler
import com.tthieu.myhelsinki.logging.Logger
import com.tthieu.myhelsinki.searchevent.domain.model.SearchParameters
import com.tthieu.myhelsinki.searchevent.domain.model.SearchResults
import com.tthieu.myhelsinki.searchevent.domain.usecases.GetSearchFilters
import com.tthieu.myhelsinki.searchevent.domain.usecases.SearchEvents
import com.tthieu.myhelsinki.searchevent.domain.usecases.SearchEventsRemotely
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class SearchEventFragmentViewModel @Inject constructor(
    private val uiEventMapper: UiEventMapper,
    private val searchEventsRemotely: SearchEventsRemotely,
    private val searchEvents: SearchEvents,
    private val getSearchFilters: GetSearchFilters,
): ViewModel() {

    private var currentPage = 0
    private var currentItemIdx = 0

    private val _state = MutableStateFlow(SearchViewState())
    private val queryStateFlow = MutableStateFlow("")
    private val langStateFlow = MutableStateFlow("")

    private var remoteSearchJob: Job = Job()

    val state: StateFlow<SearchViewState> = _state.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.PrepareForSearch -> prepareForSearch()
            else -> onSearchParametersUpdate(event)
        }
    }

    private fun onSearchParametersUpdate(event: SearchEvent) {
        remoteSearchJob.cancel(
            CancellationException("New search parameters incoming!")
        )

        when (event) {
            is SearchEvent.QueryInput -> updateQuery(event.input)
            is SearchEvent.LangValueSelected -> updateLangValue(event.lang)
            else -> Logger.d("Wrong SearchEvent in onSearchParametersUpdate!")
        }
    }

    private fun prepareForSearch() {
        loadFilterValues()
        setupSearchSubscription()
    }

    private fun setupSearchSubscription() {
        viewModelScope.launch {
            searchEvents(queryStateFlow, langStateFlow)
                .catch { onFailure(it) }
                .flowOn(Dispatchers.Main)
                .collect { onSearchResults(it) }
        }
    }

    private fun onSearchResults(searchResults: SearchResults) {
        val (events, searchParameters) = searchResults

        if (events.isEmpty()) {
            onEmptyCacheResults(searchParameters)
        } else {
            onEventList(events)
        }
    }

    private fun createExceptionHandler(message: String): CoroutineExceptionHandler {
        return viewModelScope.createExceptionHandler(message)  {
            onFailure(it)
        }
    }

    private fun setSearchingState() {
        _state.update { oldState -> oldState.updateToSearching() }
    }

    private fun setNoSearchQueryState() {
        _state.update { oldState -> oldState.updateToNoSearchQuery() }
    }

    private fun onEventList(events: List<Event>) {
        _state.update { oldState ->
            oldState.updateToHasSearchResults(events.map {
                uiEventMapper.mapToView(it)
            })
        }
    }

    private fun resetPagination() {
        currentPage = 0
    }

    private fun onPaginationInfoObtained(pagination: Pagination) {
        currentPage = pagination.lastItemIdx
    }

    private fun onFailure(throwable: Throwable) {
        _state.update { oldState ->
            if (throwable is NoMoreEventsException) {
                oldState.updateToNoResultsAvailable()
            } else {
                oldState.updateToHasFailure(throwable)
            }
        }
    }

    private fun loadFilterValues() {
        val exceptionHandler = createExceptionHandler(
            message = "Failed to get filter value!"
        )

        viewModelScope.launch(exceptionHandler) {
            val (langs) = getSearchFilters()
            updateStateWithFilterValues(langs)
        }
    }

    private fun updateStateWithFilterValues(
        langs: List<String>
    ) {
        _state.update { oldState ->
            oldState.updateToReadyToSearch(langs)
        }
    }

    private fun updateQuery(input: String) {
        resetPagination()

        queryStateFlow.value = input

        if (input.isEmpty()) {
            setNoSearchQueryState()
        } else {
            setSearchingState()
        }
    }

    private fun updateLangValue(lang: String) {
        langStateFlow.value = lang
    }

    private fun onEmptyCacheResults(searchParameters: SearchParameters) {
        _state.update { oldState ->
            oldState.updateToSearchingRemotely()
        }
        searchRemotely(searchParameters)
    }

    private fun searchRemotely(searchParameters: SearchParameters) {
        val exceptionHandler = createExceptionHandler(
            message = "Failed to search remotely."
        )

        remoteSearchJob = viewModelScope.launch(exceptionHandler) {
            Logger.d("Searching remotely...")
            val pagination = searchEventsRemotely(
                if (currentItemIdx == 0) {
                    0
                } else {
                    ++currentItemIdx
                },
                searchParameters
            )

            onPaginationInfoObtained(pagination)

            remoteSearchJob.invokeOnCompletion { it?.printStackTrace() }
        }
    }
}