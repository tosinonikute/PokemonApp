package com.pokemon.data.datasource

import com.pokemon.data.model.PokemonInfoDataModel

interface PokemonLocalSource {
    suspend fun getLocalPokemonList(): List<PokemonInfoDataModel>
}
