package com.tthieu.myhelsinki.searchevent.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tthieu.myhelsinki.R
import com.tthieu.myhelsinki.common.presentation.Event
import com.tthieu.myhelsinki.common.presentation.EventsAdapter
import com.tthieu.myhelsinki.databinding.FragmentSearchBinding
import com.tthieu.myhelsinki.searchevent.domain.usecases.GetSearchFilters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment: Fragment() {

    companion object {
        private const val ITEMS_PER_ROW = 1
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchEventFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        prepareForSearch()
    }

    private fun prepareForSearch() {
        setupFilterListeners()
        setupSearchViewListener()
        viewModel.onEvent(SearchEvent.PrepareForSearch)
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        subscribeToViewStateUpdates(adapter)
    }

    private fun createAdapter(): EventsAdapter {
        return EventsAdapter()
    }

    private fun setupRecyclerView(eventsAdapter: EventsAdapter) {
        binding.searchRecyclerView.apply {
            adapter = eventsAdapter
            layoutManager = GridLayoutManager(requireContext(), ITEMS_PER_ROW)
            setHasFixedSize(true)
        }
    }

    private fun subscribeToViewStateUpdates(searchAdapter: EventsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    updateScreenState(it, searchAdapter)
                }
            }
        }
    }

    private fun updateScreenState(newState: SearchViewState, searchAdapter: EventsAdapter) {
        val (
            inInitialState,
            searchResults,
            langFilterValues,
            searchingRemotely,
            noResultsState,
            failure
        ) = newState

        updateInitialStateView(inInitialState)
        searchAdapter.submitList(searchResults)

        with (binding.searchWidget) {
            setupFilterValues(
                language,
                langFilterValues.getContentIfNotHandled()
            )
        }

        updateRemoteSearchViews(searchingRemotely)

        handleFailures(failure)
        updateNoResultsView(noResultsState)
    }

    private fun updateNoResultsView(noResultsState: Boolean) {
        binding.noSearchResultsImageView.isVisible = noResultsState
        binding.noSearchResultsText.isVisible = noResultsState
    }

    private fun updateInitialStateView(inInitialState: Boolean) {
        binding.initialSearchImageView.isVisible = inInitialState
        binding.initialSearchText.isVisible = inInitialState
    }

    private fun handleFailures(failure: Event<Throwable>?) {
        val unhandledFailure = failure?.getContentIfNotHandled() ?: return

        val fallbackMessage = getString(R.string.an_error_occurred)
        val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        } else {
            unhandledFailure.message!!
        }

        if (snackbarMessage.isNotEmpty()) {
            Snackbar.make(
                requireView(),
                snackbarMessage,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSearchViewListener() {
        val searchView = binding.searchWidget.search

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onEvent(
                    SearchEvent.QueryInput(query.orEmpty())
                )
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onEvent(
                    SearchEvent.QueryInput(newText.orEmpty())
                )
                return true
            }
        })
    }

    private fun setupFilterListeners() {
        with(binding.searchWidget) {
            setupFilterListenerFor(language) { item ->
                viewModel
                    .onEvent(SearchEvent.LangValueSelected(item))
            }
        }
    }

    private fun setupFilterListenerFor(
        filter: AutoCompleteTextView,
        block: (item: String) -> Unit
    ) {
        filter.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                parent?.let {
                    block(it.adapter.getItem(position) as String)
                }
            }
    }

    private fun setupFilterValues(
        filter: AutoCompleteTextView,
        filterValues: List<String>?
    ) {
        if (filterValues.isNullOrEmpty()) {return}

        filter.setAdapter(createFilterAdapter(filterValues))
        filter.setText(GetSearchFilters.NO_FILTER_SELECTED, false)
    }

    private fun createFilterAdapter(
        adapterValues: List<String>
    ): ArrayAdapter<String> {
        return ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            adapterValues
        )
    }

    private fun updateRemoteSearchViews(searchingRemotely: Boolean) {
        binding.searchRemotelyProgressBar.isVisible = searchingRemotely
        binding.searchRemotelyText.isVisible = searchingRemotely
    }
}