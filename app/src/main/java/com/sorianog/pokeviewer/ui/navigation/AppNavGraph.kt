package com.sorianog.pokeviewer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sorianog.pokeviewer.ui.screens.DetailScreen
import com.sorianog.pokeviewer.ui.screens.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = AppRoutes.HOME_SCREEN) {

        composable(AppRoutes.HOME_SCREEN) {
            HomeScreen(
                onPokemonClick = { pokemonName ->
                    navController.navigateToSinglePokemon(pokemonName)
                }
            )
        }

        composable(
            route = PokemonDetail.routeWithArgs,
            arguments = PokemonDetail.arguments
        ) { navBackStackEntry ->
            val pokemonName = navBackStackEntry.arguments?.getString(PokemonDetail.pokemonNameArg)
            DetailScreen(pokemonName = pokemonName)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

private fun NavHostController.navigateToSinglePokemon(pokemonName: String) {
    this.navigateSingleTopTo("${PokemonDetail.route}/$pokemonName")
}