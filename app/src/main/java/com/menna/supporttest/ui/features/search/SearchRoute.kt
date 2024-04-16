package com.menna.supporttest.ui.features.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.menna.supporttest.ui.navigation.Screens

private val ROUTE = Screens.Search.route

fun NavGraphBuilder.searchRoute() {
    composable(route = ROUTE) { SearchScreen() }
}