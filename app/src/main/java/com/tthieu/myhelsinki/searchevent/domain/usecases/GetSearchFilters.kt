package com.tthieu.myhelsinki.searchevent.domain.usecases

import com.tthieu.myhelsinki.common.domain.model.LanguageFilter
import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.common.utils.DispatchersProvider
import com.tthieu.myhelsinki.searchevent.domain.model.SearchFilters
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class GetSearchFilters @Inject constructor(
    private val eventRepository: EventRepository,
    private val dispatchersProvider: DispatchersProvider
) {

    companion object {
        const val NO_FILTER_SELECTED = "Any"
    }

    suspend operator fun invoke(): SearchFilters {
        return withContext(dispatchersProvider.io()) {
            val unknown = LanguageFilter.unknown.name

            val langs = eventRepository.getLanguages()
                .map { lang ->
                    if (lang.name == unknown) {
                        NO_FILTER_SELECTED
                    } else {
                        lang.name
                            // .uppercase()
                            // .replaceFirstChar { firstChar ->
                            //     if (firstChar.isLowerCase()) {
                            //         firstChar.titlecase(Locale.ROOT)
                            //     } else {
                            //         firstChar.toString()
                            //     }
                            // }
                    }
                }

            return@withContext SearchFilters(langs)
        }
    }
}