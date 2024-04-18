package com.menna.supporttest.ui.features.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menna.supporttest.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    private val actionStream = MutableSharedFlow<String>()

    init {
        observeKeyword()
    }

    private fun searchForArticles() {
        _state.update { it.copy(isLoading = true, isError = false, error = null) }
        viewModelScope.launch {
            try {
                _state.update { searchUiState ->
                    searchUiState.copy(
                        isLoading = false,
                        articles = searchUseCase(
                            searchUiState.searchQuery,
                            searchUiState.page,
                        )!!.map { it.toSearchArticleUiState() }
                    )
                }
                Log.d("SearchViewModel", "searchForArticles: ${SearchUiState().articles}")
            } catch (e: Exception) {
                Log.e("Exception", "Couldn't get data: ${e.message}")
            }
        }
    }

    fun onSearchTextChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
          viewModelScope.launch {
                actionStream.emit(query)
            }
    }

    private fun resetSearch() {
        _state.update { it.copy(articles = listOf(), page = 1) }
    }

    @OptIn(FlowPreview::class)
    private fun observeKeyword() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            actionStream.debounce(700).distinctUntilChanged().filter { keyword ->
                keyword.isNotBlank()
            }.collect {
                resetSearch()
                searchForArticles()
            }
        }
    }

}