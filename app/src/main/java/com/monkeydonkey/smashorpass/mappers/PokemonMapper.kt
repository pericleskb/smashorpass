package com.monkeydonkey.smashorpass.mappers

import android.graphics.Bitmap
import com.monkeydonkey.smashorpass.models.Pokemon
import com.monkeydonkey.smashorpass.network.responses.PokemonResponse

fun PokemonResponse?.toPokemonModel(frontMain: Bitmap?, frontSecondary: Bitmap?): Pokemon {
    return Pokemon(
        id = this?.id ?: -1,
        name = this?.name ?: "",
        height = this?.height ?: -1,
        frontMain = frontMain,
        frontSecondary = frontSecondary
    )
}