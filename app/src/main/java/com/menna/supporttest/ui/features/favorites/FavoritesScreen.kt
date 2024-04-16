package com.menna.supporttest.ui.features.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.menna.supporttest.ui.composables.ItemCard

@Composable
fun FavoritesScreen() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
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