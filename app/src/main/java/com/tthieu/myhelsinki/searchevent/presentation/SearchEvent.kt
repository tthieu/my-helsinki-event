package com.tthieu.myhelsinki.searchevent.presentation

sealed class SearchEvent {
    object PrepareForSearch: SearchEvent()
    data class QueryInput(val input: String) : SearchEvent()
    data class LangValueSelected(val lang: String) : SearchEvent()
}
