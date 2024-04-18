package com.menna.supporttest.ui.features.search

import com.menna.supporttest.domain.models.Article
import kotlinx.datetime.LocalDateTime
import org.xml.sax.ErrorHandler

data class SearchUiState(
    val isSearching: Boolean = false,
    val isError: Boolean = false,
    val error: ErrorHandler? = null,
    val isLoading: Boolean = false,
    val page: Int = 1,
    val articles: List<SearchArticleUiState> = emptyList(),
    val searchQuery: String = "",
)

data class SearchArticleUiState(
    val name: String = "",
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val url: String = "",
    val urlToImage: String = "",
)

fun Article.toSearchArticleUiState(): SearchArticleUiState {
    return SearchArticleUiState(
        name = source.name,
        author = author,
        title = title,
        description = description,
        publishedAt = publishedAt.toFormattedDate(),
        url = url,
        urlToImage = urlToImage
    )
}

fun LocalDateTime.toFormattedDate() : String {
    return "$monthNumber-$dayOfMonth-$year"
}
