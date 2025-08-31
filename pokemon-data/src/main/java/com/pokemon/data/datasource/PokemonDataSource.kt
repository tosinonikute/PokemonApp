package com.pokemon.data.datasource

import com.pokemon.data.model.PokemonInfoDataModel

interface PokemonDataSource {
    suspend fun getRemotePokemonList(): List<PokemonInfoDataModel>
}
