package com.sorianog.pokeviewer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.ui.components.EmptyStateUI
import com.sorianog.pokeviewer.ui.components.LoadingIndicator
import com.sorianog.pokeviewer.ui.components.PokeDetailView
import com.sorianog.pokeviewer.ui.components.PokemonFlavorTextView
import com.sorianog.pokeviewer.ui.viewmodels.PokeDetailViewModel

@Composable
fun DetailScreen(
    pokemonDetailViewModel: PokeDetailViewModel = hiltViewModel(),
    pokemonName: String? = "0"
) {
    val pokemonDetail by pokemonDetailViewModel.pokemonDetailState.collectAsStateWithLifecycle()
    val pokemonSpecies by pokemonDetailViewModel.pokemonSpeciesState.collectAsStateWithLifecycle()

    pokemonName?.let { name ->
        LaunchedEffect(key1 = pokemonDetailViewModel) {
            pokemonDetailViewModel.getPokemonDetail(name)
            pokemonDetailViewModel.getPokemonSpecies(name)
        }
    }

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        when (pokemonSpecies) {
            is ApiState.Loading<*> -> {
                LoadingIndicator()
            }

            is ApiState.Success<*> -> {
                val species = (pokemonSpecies as ApiState.Success).data
                val flavorTextEntries = species.flavorTextEntries
                if (flavorTextEntries.isNotEmpty()) {
                    pokemonDetailViewModel.getEnglishFlavorText(flavorTextEntries)
                        ?.let { flavorText ->
                            PokemonFlavorTextView(flavorText)
                        }
                }
            }

            is ApiState.Error<*> -> {
                val error = (pokemonSpecies as ApiState.Error).error
                EmptyStateUI(
                    image = painterResource(R.drawable.ic_error),
                    message = error.toString()
                )
            }
        }
    }
}