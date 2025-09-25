package com.sorianog.pokeviewer.data.datasource

import com.sorianog.pokeviewer.data.api.PokemonApiService
import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse
import com.sorianog.pokeviewer.data.entity.PokemonSpeciesResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonDataSourceImpl @Inject constructor(
    private val apiService: PokemonApiService
) : PokemonDataSource {

    override suspend fun getPokemon(limit: Int, offset: Int): Response<AllPokemonResponse> {
        return apiService.getAllPokemon(limit, offset)
    }

    override suspend fun getPokemonDetail(name: String): Response<PokemonDetailResponse> {
        return apiService.getPokemonDetail(name)
    }

    override suspend fun getPokemonSpecies(name: String): Response<PokemonSpeciesResponse> {
        return apiService.getPokemonSpecies(name)
    }
}