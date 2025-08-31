package com.pokemon.datasource.api.datasource

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.datasource.api.mapper.PokemonDetailModelApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDataMapper
import com.pokemon.datasource.api.service.PokemonApiService

class PokemonRemoteDataSource(
    private val pokemonApiService: PokemonApiService,
    private val pokemonInfoModelApiToDataMapper: PokemonInfoModelApiToDataMapper,
    private val pokemonDetailModelApiToDataMapper: PokemonDetailModelApiToDataMapper
) : PokemonDataSource {

    override suspend fun getRemotePokemonList(): List<PokemonInfoDataModel> {
        return pokemonApiService.fetchPokemonList().results.map {
            pokemonInfoModelApiToDataMapper.map(it)
        }
    }

    override suspend fun getRemotePokemonDetail(pokemonId: Int): PokemonInfoDetailDataModel {
        return pokemonDetailModelApiToDataMapper.map(
            pokemonApiService.fetchPokemonDetail(pokemonId)
        )
    }
}
