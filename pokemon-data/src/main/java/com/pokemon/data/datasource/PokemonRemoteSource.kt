package com.pokemon.data.datasource

import com.pokemon.data.model.PokemonDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel

interface PokemonRemoteSource {
    suspend fun getRemotePokemonList(): List<PokemonInfoDataModel>
    suspend fun getRemotePokemonDetail(pokemonId: Int): PokemonInfoDetailDataModel
}
