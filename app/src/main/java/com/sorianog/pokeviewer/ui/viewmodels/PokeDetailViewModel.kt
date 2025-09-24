package com.sorianog.pokeviewer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorianog.pokeviewer.data.PokemonRepository
import com.sorianog.pokeviewer.data.api.ApiState
import com.sorianog.pokeviewer.data.entity.FlavorTextEntry
import com.sorianog.pokeviewer.data.entity.PokemonDetailResponse
import com.sorianog.pokeviewer.data.entity.PokemonSpeciesResponse
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

    private val _pokemonDetailState: MutableStateFlow<ApiState<PokemonDetailResponse>> =
        MutableStateFlow(ApiState.Loading())
    val pokemonDetailState: StateFlow<ApiState<PokemonDetailResponse>> = _pokemonDetailState

    private val _pokemonSpeciesState: MutableStateFlow<ApiState<PokemonSpeciesResponse>> =
        MutableStateFlow(ApiState.Loading())
    val pokemonSpeciesState: StateFlow<ApiState<PokemonSpeciesResponse>> = _pokemonSpeciesState

    fun getPokemonDetail(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.getPokemonDetail(name).collectLatest { pokemonDetail ->
                _pokemonDetailState.update { pokemonDetail }
            }
        }
    }

    fun getPokemonSpecies(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.getPokemonSpecies(name).collectLatest { pokemonSpecies ->
                _pokemonSpeciesState.update { pokemonSpecies }
            }
        }
    }

    fun getEnglishFlavorText(flavorTextEntries: List<FlavorTextEntry>): String? {
        return flavorTextEntries
            .first { it.language?.name == "en" }
            .flavorText?.replace(Regex("[\\n\\f]"), " ")
    }
}