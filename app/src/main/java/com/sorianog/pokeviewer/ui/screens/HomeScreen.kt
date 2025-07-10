package com.sorianog.pokeviewer.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.ui.components.EmptyStateUI
import com.sorianog.pokeviewer.ui.components.LoadingIndicator
import com.sorianog.pokeviewer.ui.components.PokeList
import com.sorianog.pokeviewer.ui.viewmodels.PokeListViewModel

@Composable
fun HomeScreen(
    pokemonListViewModel: PokeListViewModel = hiltViewModel()
) {

    val pokemonDataState by pokemonListViewModel.pokemonState.collectAsState()

    when (pokemonDataState) {
        is ApiState.Loading<*> -> {
            LoadingIndicator()
        }

        is ApiState.Success<*> -> {
            val pokemonData = (pokemonDataState as ApiState.Success).data
            if (pokemonData.results.isNotEmpty()) {
                PokeList(pokemonData.results)
            } else {
                EmptyStateUI(
                    image = painterResource(R.drawable.ic_info),
                    message = stringResource(R.string.no_pokemon)
                )
            }
        }

        is ApiState.Error<*> -> {
            val error = (pokemonDataState as ApiState.Error).error
            EmptyStateUI(
                image = painterResource(R.drawable.ic_error),
                message = error.toString()
            )
        }
    }
}