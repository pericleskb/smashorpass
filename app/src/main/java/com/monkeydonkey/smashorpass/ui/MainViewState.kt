package com.monkeydonkey.smashorpass.ui

import com.monkeydonkey.smashorpass.models.Pokemon

data class MainViewState(
    val pokeList: MutableList<Pokemon>,
    val currentIndex: Int
) {
}