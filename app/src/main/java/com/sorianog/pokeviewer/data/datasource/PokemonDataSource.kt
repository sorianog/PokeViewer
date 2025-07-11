package com.sorianog.pokeviewer.data.datasource

import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse
import retrofit2.Response

interface PokemonDataSource {

    suspend fun getPokemon(): Response<AllPokemonResponse>
    suspend fun getPokemonDetail(name: String): Response<PokemonDetailResponse>
}