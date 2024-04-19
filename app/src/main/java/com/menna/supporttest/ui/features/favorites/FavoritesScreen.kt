package com.menna.supporttest.ui.features.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.menna.supporttest.ui.composables.ItemCard

@Composable
fun FavoritesScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    FavoritesContent(state, onFavoriteClick = { viewModel.onClickFavorite(it) })
}

@Composable
fun FavoritesContent(
    state: FavoriteUiState,
    onFavoriteClick: (String) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(state.articles) { article ->
            ItemCard(
                imageUrl = article.urlToImage,
                url = article.url,
                name = article.name,
                author = article.author,
                title = article.title,
                description = article.description,
                date = article.publishedAt,
                isFavorite = article.isFavorite,
                onFavoriteClick = { onFavoriteClick(article.id) }
            )
        }
    }
}