package com.sorianog.pokeviewer.data

import com.sorianog.pokeviewer.data.datasource.PokemonDataSource
import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDataSource: PokemonDataSource
) {

    suspend fun getPokemon(): Response<AllPokemonResponse> {
        return pokemonDataSource.getPokemon()
    }
}