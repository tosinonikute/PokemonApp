package com.pokemon.data.repository

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.data.mapper.PokemonInfoDetailModelToDomainMapper
import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.domain.repository.PokemonRepository

class PokemonDataRepository(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper,
    private val pokemonInfoDetailModelToDomainMapper: PokemonInfoDetailModelToDomainMapper
) : PokemonRepository {

    override suspend fun pokemonList(): List<PokemonInfoDomainModel> {
        return pokemonDataSource.getRemotePokemonList().map {
            pokemonInfoDataModelToDomainMapper.map(it)
        }
    }

    override suspend fun pokemonDetail(pokemonId: Int): PokemonInfoDetailDomainModel {
        return pokemonInfoDetailModelToDomainMapper.map(
            pokemonDataSource.getRemotePokemonDetail(pokemonId)
        )
    }
}
