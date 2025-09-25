package com.pokemon.data.mapper

import com.pokemon.data.model.PokemonDataModel
import com.pokemon.domain.model.PokemonDomainModel

class PokemonDataToDomainMapper(
    private val pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper
) {
    fun map(model: PokemonDataModel): PokemonDomainModel {
        return PokemonDomainModel(
            pokemonList = model.pokemonList.map { pokemonInfoDataModelToDomainMapper.map(it) },
            count = model.count
        )
    }
}
