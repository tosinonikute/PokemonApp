package com.pokemon.datasource.api.datasource

import com.pokemon.data.datasource.PokemonRemoteSource
import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.datasource.api.mapper.PokemonDetailModelApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDatabaseMapper
import com.pokemon.datasource.api.service.PokemonApiService

class PokemonRemoteDataSource(
    private val pokemonApiService: PokemonApiService,
    private val pokemonInfoModelApiToDataMapper: PokemonInfoModelApiToDataMapper,
    private val pokemonDetailModelApiToDataMapper: PokemonDetailModelApiToDataMapper,
    private val pokemonInfoModelApiToDatabaseMapper: PokemonInfoModelApiToDatabaseMapper,
    private val pokemonDao: PokemonDao
) : PokemonRemoteSource {

    override suspend fun getRemotePokemonList(): List<PokemonInfoDataModel> {
        val results = pokemonApiService.fetchPokemonList().results

        // save db
        val dbResults = results.map { pokemonInfoModelApiToDatabaseMapper.map(it) }
        pokemonDao.insertPokemons(dbResults)

        return results.map {
            pokemonInfoModelApiToDataMapper.map(it)
        }
    }

    override suspend fun getRemotePokemonDetail(pokemonId: Int): PokemonInfoDetailDataModel {
        return pokemonDetailModelApiToDataMapper.map(
            pokemonApiService.fetchPokemonDetail(pokemonId)
        )
    }
}
