package com.pokemon.datasource.api.service

import com.pokemon.datasource.api.model.PokemonApiModel
import retrofit2.http.GET

interface PokemonApiService {

    @GET("pokemon")
    suspend fun fetchPokemonList(): PokemonApiModel

    companion object Companion {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}
