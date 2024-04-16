package com.menna.supporttest.ui.navigation

sealed class Screens(val route: String) {
    object Search : Screens("search")
    object Favorites : Screens("favorites")
}