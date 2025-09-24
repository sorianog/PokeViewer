package com.sorianog.pokeviewer.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesResponse(
    var id: Int?,
    var name: String?,
    @SerialName("flavor_text_entries")
    var flavorTextEntries: List<FlavorTextEntry> = emptyList<FlavorTextEntry>()
)

@Serializable
data class FlavorTextEntry(
    @SerialName("flavor_text")
    var flavorText: String? = null,
    var language: Language? = Language(),
    var version: Version? = Version()
)

@Serializable
data class Language(
    var name: String? = null,
    var url: String? = null
)

@Serializable
data class Version(
    var name: String? = null,
    var url: String? = null
)


