package com.monkeydonkey.smashorpass.network

import com.monkeydonkey.smashorpass.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

//TODO use HILT for singleton

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
//    .addNetworkInterceptor(NetworkInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object PokeApi {
    val retrofitService: PokeService by lazy {
        retrofit.create(PokeService::class.java)
    }
}
//
//class NetworkInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        try {
//            return chain.proceed(chain.request())
//        } catch (e: IOException) {
//            //TODO
//        }
//    }
//}