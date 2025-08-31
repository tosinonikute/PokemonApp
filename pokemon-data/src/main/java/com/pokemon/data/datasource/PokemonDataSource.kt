package com.pokemon.data.datasource

import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel

interface PokemonDataSource {
    suspend fun getRemotePokemonList(): List<PokemonInfoDataModel>
    suspend fun getRemotePokemonDetail(pokemonId: Int): PokemonInfoDetailDataModel
}
