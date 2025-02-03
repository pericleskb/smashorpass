package com.monkeydonkey.smashorpass.mappers

import com.monkeydonkey.smashorpass.models.PokemonList
import com.monkeydonkey.smashorpass.models.PokemonListItem
import com.monkeydonkey.smashorpass.network.responses.PokemonListItemResponse
import com.monkeydonkey.smashorpass.network.responses.PokemonListResponse

fun PokemonListResponse?.toPokemonModelList(): PokemonList {
    return  PokemonList(
        pokemonList = this?.pokemonList?.map { it -> it.toPokemonListItemModel() } ?: listOf()
    )
}

fun PokemonListItemResponse?.toPokemonListItemModel(): PokemonListItem {
    return PokemonListItem(
        name = this?.name ?: "",
        url = this?.url ?: ""
    )
}