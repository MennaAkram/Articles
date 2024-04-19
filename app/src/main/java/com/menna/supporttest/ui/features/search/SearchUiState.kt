package com.menna.supporttest.ui.features.search

import androidx.paging.PagingData
import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.models.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.datetime.LocalDateTime
import org.xml.sax.ErrorHandler

data class SearchUiState(
    val isSearching: Boolean = false,
    val isError: Boolean = false,
    val error: ErrorHandler? = null,
    val isLoading: Boolean = false,
    val page: Int = 1,
    val articles: Flow<PagingData<ArticleUiState>>? = emptyFlow(),
    val searchQuery: String = "",
)

data class ArticleUiState(
    val id: String = "",
    val name: String = "",
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val isFavorite: Boolean = false,
)

fun Article.toArticleUiState(isFavorite: Boolean): ArticleUiState {
    return ArticleUiState(
        id = source.id ?: "",
        name = source.name,
        author = author,
        title = title,
        description = description,
        publishedAt = publishedAt.toFormattedDate(),
        url = url,
        urlToImage = urlToImage,
        isFavorite = isFavorite
    )
}

fun Article.toArticleUiState(): ArticleUiState {
    return ArticleUiState(
        id = source.id ?: "",
        name = source.name,
        author = author,
        title = title,
        description = description,
        publishedAt = publishedAt.toFormattedDate(),
        url = url,
        urlToImage = urlToImage,
        isFavorite = isFavorite
    )
}

fun ArticleUiState.toArticle(): Article {
    return Article(
        source = Source(id = id, name = name),
        author = author,
        title = title,
        description = description,
        publishedAt = LocalDateTime(
            publishedAt.split("-")[2].toInt(),
            publishedAt.split("-")[0].toInt(),
            publishedAt.split("-")[1].toInt(),
            0,
            0,
            0,
            0
        ),
        url = url,
        urlToImage = urlToImage,
        isFavorite = isFavorite
    )
}

fun LocalDateTime.toFormattedDate(): String {
    return "$monthNumber-$dayOfMonth-$year"
}
