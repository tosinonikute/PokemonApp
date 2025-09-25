package com.pokemon.datasource.api.datasource

import com.pokemon.data.datasource.PokemonLocalSource
import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.datasource.api.mapper.PokemonInfoModelDatabaseToDataMapper

class PokemonLocalDataSource(
    private val pokemonInfoModelDatabaseToDataMapper: PokemonInfoModelDatabaseToDataMapper,
    private val pokemonDao: PokemonDao
) : PokemonLocalSource {

    override suspend fun getLocalPokemonList(): List<PokemonInfoDataModel> {
        return pokemonDao.findAll().map {
            pokemonInfoModelDatabaseToDataMapper.map(it)
        }
    }
}
