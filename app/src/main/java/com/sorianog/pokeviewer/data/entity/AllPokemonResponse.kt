package com.sorianog.pokeviewer.data.entity

data class AllPokemonResponse(
    var count    : Int?               = null,
    var next     : String?            = null,
    var previous : String?            = null,
    var results  : ArrayList<PokeResults> = arrayListOf()
)

data class PokeResults(
    var name : String? = null,
    var url  : String? = null
)