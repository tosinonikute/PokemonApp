package com.pokemon.data.repository

import com.pokemon.data.datasource.PokemonLocalSource
import com.pokemon.data.datasource.PokemonRemoteSource
import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.data.mapper.PokemonInfoDetailModelToDomainMapper
import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDataRepository(
    private val pokemonRemoteSource: PokemonRemoteSource,
    private val pokemonLocalSource: PokemonLocalSource,
    private val pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper,
    private val pokemonInfoDetailModelToDomainMapper: PokemonInfoDetailModelToDomainMapper
) : PokemonRepository {

    override suspend fun pokemonList(): List<PokemonInfoDomainModel> {
        return withContext(Dispatchers.IO) {
            try {
                // Try to fetch from remote source first
                val remoteData = pokemonRemoteSource.getRemotePokemonList()
                remoteData.map { pokemonInfoDataModelToDomainMapper.map(it) }
            } catch (exception: Exception) {
                // If remote fails, fallback to local source
                val localData = pokemonLocalSource.getLocalPokemonList()
                localData.map { pokemonInfoDataModelToDomainMapper.map(it) }
            }
        }
    }

    override suspend fun pokemonDetail(pokemonId: Int): PokemonInfoDetailDomainModel {
        return pokemonInfoDetailModelToDomainMapper.map(
            pokemonRemoteSource.getRemotePokemonDetail(pokemonId)
        )
    }
}
