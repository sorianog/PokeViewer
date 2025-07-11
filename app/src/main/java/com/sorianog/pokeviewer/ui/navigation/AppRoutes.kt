package com.sorianog.pokeviewer.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object AppRoutes {

    const val HOME_SCREEN = "HOME"
    const val DETAIL_SCREEN = "DETAIL"
}

object PokemonDetail {
    val route = AppRoutes.DETAIL_SCREEN
    const val pokemonNameArg = "pokemon_name"
    val routeWithArgs = "$route/{${pokemonNameArg}}"
    val arguments = listOf(
        navArgument(pokemonNameArg) { type = NavType.StringType }
    )
}