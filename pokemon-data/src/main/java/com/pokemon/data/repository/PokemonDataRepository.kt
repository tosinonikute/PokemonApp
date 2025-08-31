package com.pokemon.data.repository

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.domain.repository.PokemonRepository

class PokemonDataRepository(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper
) : PokemonRepository {

    override suspend fun pokemonList(): List<PokemonInfoDomainModel> {
        return pokemonDataSource.getRemotePokemonList().map {
            pokemonInfoDataModelToDomainMapper.map(it)
        }
    }
}
