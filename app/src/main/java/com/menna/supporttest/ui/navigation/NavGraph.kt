package com.menna.supporttest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.menna.supporttest.ui.LocalNavigationProvider
import com.menna.supporttest.ui.features.favorites.favoritesRoute
import com.menna.supporttest.ui.features.search.searchRoute

@Composable
fun NavGraph() {
    val navController = LocalNavigationProvider.current

    NavHost(
        navController = navController,
        startDestination = Screens.Search.route
    ) {
        searchRoute()
        favoritesRoute()
    }
}