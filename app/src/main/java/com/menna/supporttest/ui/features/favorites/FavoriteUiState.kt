package com.menna.supporttest.ui.features.favorites

import com.menna.supporttest.ui.features.search.ArticleUiState
import org.xml.sax.ErrorHandler

data class FavoriteUiState(
    val isError: Boolean = false,
    val error: ErrorHandler? = null,
    val isLoading: Boolean = false,
    val articles: List<ArticleUiState> = emptyList()
)
