package com.menna.supporttest.ui.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menna.supporttest.domain.usecase.FavoriteUseCase
import com.menna.supporttest.ui.features.search.toArticle
import com.menna.supporttest.ui.features.search.toArticleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(FavoriteUiState())
    val state = _state.asStateFlow()

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            favoriteUseCase.getAllFavorites().collectLatest { articles ->
                _state.update {
                    it.copy( articles = articles.map { it.toArticleUiState() }, isLoading = false)
                }
            }
        }
    }

    fun onClickFavorite(id: String) {
        viewModelScope.launch {
            val article = _state.value.articles.find { it.id == id }
            article?.let {
                favoriteUseCase.toggleFavorite(it.toArticle())
            }
        }
    }
}