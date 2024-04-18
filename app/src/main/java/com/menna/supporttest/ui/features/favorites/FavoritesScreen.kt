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
                imageUrl = "https://www.cnet.com/a/img/resize/92bf4bf551dc8c1d5f44773a5e4e9df652212b2f/hub/2024/03/21/63570a8f-06ac-43bb-b71b-bea10520a215/gettyimages-1321935762.jpg?auto=webp&fit=crop&height=675&width=1200",
                name = "name",
                author = "author",
                title = "title",
                description = "description",
                date = "date",
            )
        }
    }
}