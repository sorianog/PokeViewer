package com.sorianog.pokeviewer.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sorianog.pokeviewer.data.PokemonRepository
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.data.entity.PokeResult
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val repository: PokemonRepository
) : PagingSource<Int, PokeResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeResult> {
        return try {
            val position = params.key ?: 0
            var pokemonResults = listOf<PokeResult>()

            repository.getPokemon(limit = params.loadSize, offset = position)
                .collectLatest { allPokemonResponse ->
                    if (allPokemonResponse is ApiState.Success<*>) {
                        val pokemonData = (allPokemonResponse as ApiState.Success).data
                        if (pokemonData.results.isNotEmpty()) {
                            pokemonResults = pokemonData.results
                        }
                    }
                }
            if (pokemonResults.isNotEmpty()) {
                LoadResult.Page(
                    data = pokemonResults,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = position + params.loadSize
                )
            } else {
                LoadResult.Error(Exception("Unable to retrieve Pokemon"))
            }
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokeResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}