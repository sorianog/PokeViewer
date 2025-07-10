package com.sorianog.pokeviewer.data.datasource

import com.sorianog.pokeviewer.data.api.PokemonApiService
import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonDataSourceImpl @Inject constructor(
    private val apiService: PokemonApiService
) : PokemonDataSource {

    override suspend fun getPokemon(): Response<AllPokemonResponse> {
        return apiService.getAllPokemon()
    }
}