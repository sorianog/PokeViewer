package com.sorianog.pokeviewer.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    var id: Int,
    var name: String,
    var types: List<PokeTypes>,
    var sprites: PokeSprites
)

@Serializable
data class PokeTypes(
    var slot: Int? = null,
    var type: PokeType
)

@Serializable
data class PokeType(
    var name: String,
    var url: String? = null
)

@Serializable
data class PokeSprites(
    @SerialName("back_default")
    var backDefault: String,
    @SerialName("front_default")
    var frontDefault: String
)

