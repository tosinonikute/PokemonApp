package com.pokemon.data.datasource

import com.pokemon.data.model.PokemonDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel

interface PokemonDataSource {
    suspend fun getRemotePokemonList(offset: Int, limit: Int): PokemonDataModel
    suspend fun getRemotePokemonDetail(pokemonId: Int): PokemonInfoDetailDataModel
}
