package com.sorianog.pokeviewer.data.api

import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit") limit: Int = 151,
        @Query("offset") offset: Int = 0
    ) : Response<AllPokemonResponse>
}