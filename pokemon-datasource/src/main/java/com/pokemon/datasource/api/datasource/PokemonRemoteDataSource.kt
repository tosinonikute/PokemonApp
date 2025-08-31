package com.pokemon.datasource.api.datasource

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDataMapper
import com.pokemon.datasource.api.service.PokemonApiService

class PokemonRemoteDataSource(
    private val pokemonApiService: PokemonApiService,
    private val pokemonInfoModelApiToDataMapper: PokemonInfoModelApiToDataMapper
) : PokemonDataSource {

    override suspend fun getRemotePokemonList(): List<PokemonInfoDataModel> {
        return pokemonApiService.fetchPokemonList().results.map {
            pokemonInfoModelApiToDataMapper.map(it)
        }
    }
}
