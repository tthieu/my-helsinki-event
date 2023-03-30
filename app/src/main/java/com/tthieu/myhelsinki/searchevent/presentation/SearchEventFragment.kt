package com.tthieu.myhelsinki.searchevent.presentation

import androidx.fragment.app.Fragment
import com.tthieu.myhelsinki.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    companion object {
        private const val ITEMS_PER_ROW = 1
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding

    private
}