package com.sorianog.pokeviewer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorianog.pokeviewer.data.PokemonRepository
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.data.entity.AllPokemonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
}