package com.pokemon.data.repository

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.mapper.PokemonDataToDomainMapper
import com.pokemon.data.mapper.PokemonInfoDetailModelToDomainMapper
import com.pokemon.domain.model.PokemonDomainModel
import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.repository.PokemonRepository

class PokemonDataRepository(
    private val pokemonDataSource: PokemonDataSource,
    private val pokemonInfoDetailModelToDomainMapper: PokemonInfoDetailModelToDomainMapper,
    private val pokemonDataToDomainMapper: PokemonDataToDomainMapper
) : PokemonRepository {

    override suspend fun pokemonList(offset: Int, limit: Int): PokemonDomainModel {
        return pokemonDataToDomainMapper.map(pokemonDataSource.getRemotePokemonList(offset, limit))
    }

    override suspend fun pokemonDetail(pokemonId: Int): PokemonInfoDetailDomainModel {
        return pokemonInfoDetailModelToDomainMapper.map(
            pokemonDataSource.getRemotePokemonDetail(pokemonId)
        )
    }
}

