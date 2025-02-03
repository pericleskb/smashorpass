package com.monkeydonkey.smashorpass.network.responses

import com.google.gson.annotations.SerializedName

class PokemonListResponse(
    @SerializedName("results") val pokemonList: List<PokemonListItemResponse>
) {
}

class PokemonListItemResponse(
    val name: String,
    val url: String
)