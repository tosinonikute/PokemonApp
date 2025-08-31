package com.pokemon.datasource.api.service

import com.pokemon.datasource.api.model.PokemonApiModel
import com.pokemon.datasource.api.model.PokemonInfoDetailApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("pokemon")
    suspend fun fetchPokemonList(): PokemonApiModel

    @GET("pokemon/{pokemon_id}")
    suspend fun fetchPokemonDetail(@Path("pokemon_id") pokemonId: Int): PokemonInfoDetailApiModel

    companion object Companion {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}
