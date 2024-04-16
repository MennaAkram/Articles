package com.menna.supporttest.ui.features.bottom_navigation

import com.menna.supporttest.R
import com.menna.supporttest.ui.navigation.Screens

sealed class BottomBarItems(
    val route: String,
    val label: Int,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
) {
    object Search : BottomBarItems(
        route = Screens.Search.route,
        label = R.string.search,
        selectedIcon = R.drawable.search,
        unSelectedIcon = R.drawable.search,
    )

    object Favorites : BottomBarItems(
        route = Screens.Favorites.route,
        label = R.string.favorites,
        selectedIcon = R.drawable.heart,
        unSelectedIcon = R.drawable.heart,
    )
}