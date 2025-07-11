package com.sorianog.pokeviewer.ui.components

import androidx.compose.runtime.Composable
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse

@Composable
fun PokeDetailView(
    pokemonDetail: PokemonDetailResponse
) {
    println("### detail: $pokemonDetail")
}