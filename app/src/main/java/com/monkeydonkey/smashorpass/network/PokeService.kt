package com.monkeydonkey.smashorpass.network

import com.monkeydonkey.smashorpass.network.responses.PokemonListResponse
import com.monkeydonkey.smashorpass.network.responses.PokemonResponse

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String,
    ): PokemonResponse

    @GET
    suspend fun downloadImage(@Url imageUrl: String): ResponseBody
}