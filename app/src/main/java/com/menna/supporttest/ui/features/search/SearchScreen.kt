package com.menna.supporttest.ui.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.menna.supporttest.ui.composables.ItemCard
import com.menna.supporttest.ui.features.search.composables.SearchBar

@Composable
fun SearchScreen() {
SearchContent()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier.padding(16.dp) , verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SearchBar( value = "Search", onValueChange = {})
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                ItemCard(
                    name = "name",
                    author = "author",
                    title = "title",
                    description = "description",
                    date = "date",
                )
            }
        }
    }
}