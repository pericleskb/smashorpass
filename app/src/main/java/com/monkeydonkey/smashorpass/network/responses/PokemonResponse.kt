package com.monkeydonkey.smashorpass.network.responses

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int, //In decimeters. Divide by 10 to get meters
    val sprites: Sprites
) {
}

data class Sprites(
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("other") val otherSprites: OtherSprites,
)

data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String
)