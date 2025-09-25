package com.sorianog.pokeviewer.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sorianog.pokeviewer.R
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.ui.components.EmptyStateUI
import com.sorianog.pokeviewer.ui.components.LoadingIndicator
import com.sorianog.pokeviewer.ui.components.PokeList
import com.sorianog.pokeviewer.ui.components.PokePagedList
import com.sorianog.pokeviewer.ui.viewmodels.PokeListViewModel

@Composable
fun HomeScreen(
    pokemonListViewModel: PokeListViewModel = hiltViewModel(),
    onPokemonClick: (String) -> Unit
) {

    val pokemonDataState by pokemonListViewModel.pokemonState.collectAsState()

    when (pokemonDataState) {
        is ApiState.Loading<*> -> {
            LoadingIndicator()
        }

        is ApiState.Success<*> -> {
            val pokemonData = (pokemonDataState as ApiState.Success).data
            if (pokemonData.results.isNotEmpty()) {
                // Uncomment to use static list
//                PokeList(pokemonData.results, onPokemonClick)
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

    val pokemonPagedItems = pokemonListViewModel.getPokemonDataPager().collectAsLazyPagingItems()
    PokePagedList(pokemonPagedItems, onPokemonClick)
}