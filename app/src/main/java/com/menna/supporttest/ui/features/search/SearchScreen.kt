package com.menna.supporttest.ui.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.menna.supporttest.R
import com.menna.supporttest.ui.composables.EmptyPlaceholder
import com.menna.supporttest.ui.composables.ItemCard
import com.menna.supporttest.ui.composables.Loading
import com.menna.supporttest.ui.composables.NoInternetPlaceholder
import com.menna.supporttest.ui.features.search.composables.SearchBar

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    SearchContent(
        state = state,
        listener = viewModel,
    )
}

@Composable
fun SearchContent(
    state: SearchUiState,
    listener: ArticleInteractionListener,
    modifier: Modifier = Modifier,
) {
    val items = state.articles?.collectAsLazyPagingItems()

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchBar(
            value = state.searchQuery,
            onValueChange = { listener.onChangeSearchingText(it) })
        if (state.isError){
            NoInternetPlaceholder(painter = painterResource(R.drawable.warning),
                text = "Check your internet connection and try again.",
                onClickTryAgain = { listener.onClickTryAgain() })
        }
        if (items == null || items.itemCount == 0 && !state.isError) {
            EmptyPlaceholder(
                painter = painterResource(R.drawable.searching_placeholder),
                text = "Search for articles"
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(items.itemCount, key = { items[it]?.id ?: "" }) {
                    ItemCard(
                        imageUrl = items[it]?.urlToImage ?: "",
                        url = items[it]?.url ?: "",
                        name = items[it]?.name ?: "",
                        author = items[it]?.author ?: "",
                        title = items[it]?.title ?: "",
                        description = items[it]?.description ?: "",
                        date = items[it]?.publishedAt ?: "",
                        isFavorite = items[it]?.isFavorite ?: false,
                        onFavoriteClick = { listener.onClickFavorite(items[it]?.id ?: "") },
                    )
                }
                item { Loading(state.isLoading) }
            }
        }
    }
}
