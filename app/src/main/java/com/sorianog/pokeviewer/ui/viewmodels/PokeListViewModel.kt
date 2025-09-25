package com.sorianog.pokeviewer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sorianog.pokeviewer.data.PokemonRepository
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.data.datasource.PokemonPagingSource
import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import com.sorianog.pokeviewer.data.entity.PokeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonState: MutableStateFlow<ApiState<AllPokemonResponse>> =
        MutableStateFlow(ApiState.Loading())
    val pokemonState: StateFlow<ApiState<AllPokemonResponse>> = _pokemonState

    init {
        getPokemon()
    }

    private fun getPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.getPokemon().collectLatest { pokemon ->
                _pokemonState.update { pokemon }
            }
        }
    }

    fun getPokemonDataPager(): Flow<PagingData<PokeResult>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PokemonPagingSource(pokemonRepository) }
        ).flow.cachedIn(viewModelScope)
    }
}