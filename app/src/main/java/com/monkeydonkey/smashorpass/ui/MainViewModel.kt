package com.monkeydonkey.smashorpass.ui

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monkeydonkey.smashorpass.mappers.toPokemonModel
import com.monkeydonkey.smashorpass.mappers.toPokemonModelList
import com.monkeydonkey.smashorpass.models.Pokemon
import com.monkeydonkey.smashorpass.network.PokeApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainViewModel: ViewModel() {

    private val _mainState = MutableStateFlow(MainViewState(mutableListOf(), 0))
    val mainState: StateFlow<MainViewState> = _mainState.asStateFlow()

    init {
        //TODO difference between this CoroutineScope(Dispatchers.IO) and ViewModelScope
        viewModelScope.launch {
            try {
                val pokeList = PokeApi.retrofitService.getPokemonList(10, 0).toPokemonModelList()
                val pokemons = pokeList.pokemonList.map { it ->
                    async { getPokemon(it.name) } //TODO read on async and awaitAll
                }.awaitAll()

                _mainState.update { currentState -> currentState.copy(
                    pokeList = pokemons as MutableList
                ) }
            }
            catch (e: Exception) {
                Log.e("POKEMON", e.toString())
            }
        }
    }

    //Chain each pokemon call with synchronized calls to get their bitmaps
    private suspend fun getPokemon(name: String): Pokemon {
        val pokemonResponse = PokeApi.retrofitService.getPokemon(name)
        //supervisorScope will not cancel other coroutines if one fails
        //TODO what does using the scope here do?
        //TODO how does this return work?
        val getImagesScope = supervisorScope {
            val frontDefaultDeferred = async { PokeApi.retrofitService
                .downloadImage(pokemonResponse.sprites.frontDefault)}
            val frontArtworkDeferred = async { PokeApi.retrofitService
                .downloadImage(pokemonResponse.sprites.otherSprites.officialArtwork.frontDefault)}
            //TODO check syntax
            val (frontDefaultResponse, frontArtworkResponse) = awaitAll(frontDefaultDeferred, frontArtworkDeferred)
            return@supervisorScope pokemonResponse.toPokemonModel(
                BitmapFactory.decodeStream(frontDefaultResponse.byteStream()),
                BitmapFactory.decodeStream(frontArtworkResponse.byteStream())
            )
        }
        return getImagesScope
    }

}