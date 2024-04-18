package com.menna.supporttest.ui.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.menna.supporttest.ui.composables.ItemCard
import com.menna.supporttest.ui.features.search.composables.SearchBar

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    SearchContent(state = state, onTextChange = viewModel::onSearchTextChange)
}

@Composable
fun SearchContent(
    state: SearchUiState,
    onTextChange: (String) -> Unit,
//    listener: SearchInteractionListener,
    modifier: Modifier = Modifier,
) {
    Column (modifier = modifier.padding(16.dp) , verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SearchBar( value = state.searchQuery, onValueChange = onTextChange)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.articles.size) {
                val article = state.articles[it]
                ItemCard(
                    imageUrl = article.urlToImage,
                    name = article.name,
                    author = article.author,
                    title = article.title,
                    description = article.description,
                    date = article.publishedAt,
                )
            }
        }
    }
}