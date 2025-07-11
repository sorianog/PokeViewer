package com.sorianog.pokeviewer.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.ui.components.EmptyStateUI
import com.sorianog.pokeviewer.ui.components.LoadingIndicator
import com.sorianog.pokeviewer.ui.components.PokeDetailView
import com.sorianog.pokeviewer.ui.viewmodels.PokeDetailViewModel

@Composable
fun DetailScreen(
    pokemonDetailViewModel: PokeDetailViewModel = hiltViewModel(),
    pokemonName: String? = "0"
) {
    val pokemonDetail by pokemonDetailViewModel.pokemonDetailState.collectAsStateWithLifecycle()

    pokemonName?.let { name ->
        LaunchedEffect(key1 = pokemonDetailViewModel) {
            pokemonDetailViewModel.getPokemonDetail(name)
        }
    }

    when (pokemonDetail) {
        is ApiState.Loading<*> -> {
            LoadingIndicator()
        }

        is ApiState.Success<*> -> {
            val detail = (pokemonDetail as ApiState.Success).data
            PokeDetailView(detail)
        }

        is ApiState.Error<*> -> {
            val error = (pokemonDetail as ApiState.Error).error
            EmptyStateUI(
                image = painterResource(R.drawable.ic_error),
                message = error.toString()
            )
        }
    }
}