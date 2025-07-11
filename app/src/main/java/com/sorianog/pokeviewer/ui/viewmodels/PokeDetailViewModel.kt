package com.sorianog.pokeviewer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorianog.pokeviewer.data.PokemonRepository
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonDetailState: MutableStateFlow<ApiState<PokemonDetailResponse>> = MutableStateFlow(ApiState.Loading())
    val pokemonDetailState: StateFlow<ApiState<PokemonDetailResponse>> = _pokemonDetailState

    fun getPokemonDetail(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.getPokemonDetail(name).collectLatest { pokemonDetail ->
                _pokemonDetailState.update { pokemonDetail }
            }
        }
    }
}