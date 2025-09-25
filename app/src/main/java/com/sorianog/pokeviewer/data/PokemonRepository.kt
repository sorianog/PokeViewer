package com.sorianog.pokeviewer.data

import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.data.datasource.PokemonDataSource
import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse
import com.sorianog.pokeviewer.data.entity.PokemonSpeciesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDataSource: PokemonDataSource
) {

    fun getPokemon(limit: Int = 151, offset: Int = 0): Flow<ApiState<AllPokemonResponse>> {
        return flow {
            emit(ApiState.Loading())

            val response = pokemonDataSource.getPokemon(limit, offset)

            if (response.isSuccessful && response.body() != null) {
                emit(ApiState.Success(response.body()!!))
            } else {
                emit(ApiState.Error("Error fetching Pokemon: ${response.code()}"))
            }
        }.catch { err ->
            emit(ApiState.Error(err.localizedMessage ?: "Error in flow occurred"))
        }
    }

    fun getPokemonDetail(name: String): Flow<ApiState<PokemonDetailResponse>> {
        return flow {
            emit(ApiState.Loading())

            val response = pokemonDataSource.getPokemonDetail(name)

            if (response.isSuccessful && response.body() != null) {
                emit(ApiState.Success(response.body()!!))
            } else {
                emit(ApiState.Error("Error fetching Pokemon details: ${response.code()}"))
            }
        }.catch { err ->
            emit(ApiState.Error(err.localizedMessage ?: "Error in flow occurred"))
        }
    }

    fun getPokemonSpecies(name: String): Flow<ApiState<PokemonSpeciesResponse>> {
        return flow {
            emit(ApiState.Loading())

            val response = pokemonDataSource.getPokemonSpecies(name)

            if (response.isSuccessful && response.body() != null) {
                emit(ApiState.Success(response.body()!!))
            } else {
                emit(ApiState.Error("Error fetching Pokemon species: ${response.code()}"))
            }
        }.catch { err ->
            emit(ApiState.Error(err.localizedMessage ?: "Error in flow occurred"))
        }
    }
}