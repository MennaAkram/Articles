package com.menna.supporttest.ui.features.favorites

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.menna.supporttest.ui.navigation.Screens

private val ROUTE = Screens.Favorites.route

fun NavGraphBuilder.favoritesRoute() {
    composable(route = ROUTE) { FavoritesScreen() }
}