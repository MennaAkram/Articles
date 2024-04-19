package com.menna.supporttest.ui.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.menna.supporttest.domain.usecase.FavoriteUseCase
import com.menna.supporttest.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val favoriteUseCase: FavoriteUseCase,
) : ViewModel(), ArticleInteractionListener {
    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    private val favoriteIds = mutableSetOf<String>()

    private val actionStream = MutableSharedFlow<String>()

    init {
        observeKeyword()
        observeFavorite()
    }

    private fun searchForArticles() {
        _state.update { it.copy(isLoading = true, isError = false, error = null) }
        viewModelScope.launch(Dispatchers.IO) {
            val data = searchUseCase(
                state.value.searchQuery, state.value.page,
            ).map { pagingData ->
                pagingData.map {
                    it.toArticleUiState(isFavorite = favoriteIds.contains(it.source.id))
                }
            }.cachedIn(viewModelScope)
            _state.update { searchUiState ->
                searchUiState.copy(
                    isLoading = false,
                    isSearching = true,
                    articles = data
                )
            }
        }
    }

    override fun onClickFavorite(id: String) {
        _state.update {
            it.copy(articles =
            state.value.articles?.map { pagingData ->
                pagingData.map { article ->
                    if (article.id == id) {
                        favoriteUseCase.toggleFavorite(article.toArticle())
                        article.copy(isFavorite = !article.isFavorite)
                    } else {
                        article
                    }
                }
            }
            )
        }
    }

    override fun onChangeSearchingText(query: String) {
        _state.update { it.copy(searchQuery = query) }
        viewModelScope.launch {
            actionStream.emit(query)
        }
    }

    override fun onClickTryAgain() {
        searchForArticles()
    }

    private fun resetSearch() {
        _state.update { it.copy(articles = flowOf(), page = 1) }
    }

    @OptIn(FlowPreview::class)
    private fun observeKeyword() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            actionStream.debounce(500).distinctUntilChanged().filter { keyword ->
                keyword.isNotBlank()
            }.collect {
                resetSearch()
                searchForArticles()
            }
        }
    }

    private fun observeFavorite() {
        viewModelScope.launch {
            favoriteUseCase.getAllFavorites().map {
                it.map { it.source.id }
            }.collectLatest { articles ->
                favoriteIds.clear()
                favoriteIds.addAll(articles.filterNotNull())
                searchForArticles()
            }
        }
    }
}