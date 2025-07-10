package com.sorianog.pokeviewer.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class AllPokemonResponse(
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: List<PokeResult>
)

@Serializable
data class PokeResult(
    var name: String? = null,
    var url: String? = null
)