package com.monkeydonkey.smashorpass.models

data class PokemonList(
    val pokemonList: List<PokemonListItem>
)

data class PokemonListItem(
    val name: String,
    val url: String
)