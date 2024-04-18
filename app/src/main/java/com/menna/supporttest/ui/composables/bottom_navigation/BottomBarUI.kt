package com.menna.supporttest.ui.composables.bottom_navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.menna.supporttest.ui.LocalNavigationProvider
import com.menna.supporttest.ui.navigation.Screens
import com.menna.supporttest.ui.theme.Black60
import com.menna.supporttest.ui.theme.Primary

@Composable
fun BottomBarUI() {
    val navController = LocalNavigationProvider.current

    val screens = listOf(
        BottomBarItems.Search,
        BottomBarItems.Favorites,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

            NavigationBar(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                tonalElevation = 0.dp,
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItems,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    NavigationBarItem(
        icon = {
            Icon(
                painter =
                if (selected) painterResource(id = screen.selectedIcon)
                else painterResource(id = screen.unSelectedIcon),
                contentDescription = "icon",
                tint = if (selected) Primary else Black60
            )
        },
        selected = selected,
        label = {
            Text(
                text = stringResource(id = screen.label),
                color = if (selected) Primary else Black60
            )
        },
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            when (screen) {
                BottomBarItems.Search -> {
                    navController.popBackStack(Screens.Search.route, false)
                }

                BottomBarItems.Favorites -> {
                    navController.popBackStack(Screens.Favorites.route, false)
                }
            }

        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Primary,
            indicatorColor = MaterialTheme.colorScheme.onTertiary,
            unselectedIconColor = Black60,
            selectedTextColor = Primary,
            unselectedTextColor = Black60
        )
    )
}