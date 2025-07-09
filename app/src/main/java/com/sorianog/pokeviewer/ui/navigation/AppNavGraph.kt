package com.sorianog.pokeviewer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sorianog.pokeviewer.ui.screens.DetailScreen
import com.sorianog.pokeviewer.ui.screens.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.HOME_SCREEN) {

        composable(AppRoutes.HOME_SCREEN) {
            HomeScreen()
        }

        composable(AppRoutes.DETAIL_SCREEN) {
            DetailScreen()
        }
    }
}