package com.sorianog.pokeviewer.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.ui.components.PokeList
import com.sorianog.pokeviewer.ui.viewmodels.PokeListViewModel

@Composable
fun HomeScreen(
    pokemonListViewModel: PokeListViewModel = hiltViewModel()
) {

    val pokemonDataState by pokemonListViewModel.pokemonState.collectAsState()

    when (pokemonDataState) {
        is ApiState.Loading<*> -> {
            // Display loading component
        }

        is ApiState.Success<*> -> {
            val pokemonData = (pokemonDataState as ApiState.Success).data
            if (pokemonData.results.isNotEmpty()) {
                println("### pokemon: ${pokemonData.results}")
                PokeList(pokemonData.results)
            } else {
                // Display empty message
            }
        }

        is ApiState.Error<*> -> {
            // Display error component
            val error = (pokemonDataState as ApiState.Error).error
            println("### error: $error")
        }
    }
}