package com.menna.supporttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.menna.supporttest.ui.LocalNavigationProvider
import com.menna.supporttest.ui.composables.bottom_navigation.BottomBarUI
import com.menna.supporttest.ui.navigation.NavGraph
import com.menna.supporttest.ui.theme.SupportTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
            SupportTestTheme {
                Scaffold (
                    bottomBar = { BottomBarUI() }
                        ){ innerPadding ->
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.onTertiary)
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        NavGraph()
                    }
                }
            }
            }
        }
    }
}