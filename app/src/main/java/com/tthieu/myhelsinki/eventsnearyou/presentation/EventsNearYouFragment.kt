package com.tthieu.myhelsinki.eventsnearyou.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tthieu.myhelsinki.R
import com.tthieu.myhelsinki.common.presentation.Event
import com.tthieu.myhelsinki.common.presentation.EventsAdapter
import com.tthieu.myhelsinki.databinding.FragmentEventsNearYouBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsNearYouFragment : Fragment()  {

    companion object {
        private const val ITEMS_PER_NOW = 2
    }

    private val viewModel: EventsNearYouFragmentViewModel by viewModels()
    private val binding get() = _binding!!

    private var _binding: FragmentEventsNearYouBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsNearYouBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        requestInitialEventsList()
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        subscribeToViewStateUpdates(adapter)
    }

    private fun createAdapter(): EventsAdapter {
        return EventsAdapter()
    }

    private fun setupRecyclerView(eventsNearYouAdapter: EventsAdapter) {
        binding.eventsRecyclerView.apply {
            adapter = eventsNearYouAdapter
            layoutManager = GridLayoutManager(requireContext(), ITEMS_PER_NOW)
            setHasFixedSize(true)
            addOnScrollListener(createInfiniteScrollListener(layoutManager as GridLayoutManager))
        }
    }

    private fun createInfiniteScrollListener(
        layoutManager: GridLayoutManager
    ): RecyclerView.OnScrollListener {
        return object : InfiniteScrollListener(
            layoutManager = layoutManager,
            EventsNearYouFragmentViewModel.UI_PAGE_SIZE
        ) {
            override fun loadMoreItems() {
                requestMoreEvents()
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoadingMoreEvents
            }

            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }
        }
    }

    private fun requestMoreEvents() {
        viewModel.onEvent(EventsNearYouEvent.RequestMoreEvents)
    }

    private fun subscribeToViewStateUpdates(adapter: EventsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    updateScreenState(it, adapter)
                }
            }
        }
    }

    private fun updateScreenState(state: EventsNearYouViewState, adapter: EventsAdapter) {
        binding.progressBar.isVisible = state.loading
        adapter.submitList(state.events)
        handleNoMoreEventsNearby(state.noMoreEventsNearby)
        handleFailures(state.failure)
    }

    private fun handleNoMoreEventsNearby(noMoreEventsNearby: Boolean) {
        // Show a warning message and a prompt for the user to try a different
        // distance or position
    }

    private fun handleFailures(failure: Event<Throwable>?) {
        val unhandledFailure = failure?.getContentIfNotHandled() ?: return

        val fallbackMessage = getString(R.string.an_error_occurred)
        val snackBarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        } else {
            unhandledFailure.message!!
        }

        if (snackBarMessage.isNotEmpty()) {
            Snackbar.make(requireView(), snackBarMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun requestInitialEventsList() {
        viewModel.onEvent(EventsNearYouEvent.RequestInitialEventsList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}