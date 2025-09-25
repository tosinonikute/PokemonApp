package com.pokemon.datasource.api.datasource

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.model.PokemonDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.datasource.api.mapper.PokemonApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonDetailModelApiToDataMapper
import com.pokemon.datasource.api.service.PokemonApiService

class PokemonRemoteDataSource(
    private val pokemonApiService: PokemonApiService,
    private val pokemonDetailModelApiToDataMapper: PokemonDetailModelApiToDataMapper,
    private val pokemonApiToDataMapper: PokemonApiToDataMapper
) : PokemonDataSource {

    override suspend fun getRemotePokemonList(offset: Int, limit: Int): PokemonDataModel {
        return pokemonApiToDataMapper.map(pokemonApiService.fetchPokemonList(offset, limit))
    }

    override suspend fun getRemotePokemonDetail(pokemonId: Int): PokemonInfoDetailDataModel {
        return pokemonDetailModelApiToDataMapper.map(
            pokemonApiService.fetchPokemonDetail(pokemonId)
        )
    }
}
