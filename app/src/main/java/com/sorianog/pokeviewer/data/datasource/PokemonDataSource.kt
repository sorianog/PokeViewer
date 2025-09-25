package com.sorianog.pokeviewer.data.datasource

import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse
import com.sorianog.pokeviewer.data.entity.PokemonSpeciesResponse
import retrofit2.Response

interface PokemonDataSource {

    suspend fun getPokemon(limit: Int, offset: Int): Response<AllPokemonResponse>
    suspend fun getPokemonDetail(name: String): Response<PokemonDetailResponse>
    suspend fun getPokemonSpecies(name: String): Response<PokemonSpeciesResponse>
}