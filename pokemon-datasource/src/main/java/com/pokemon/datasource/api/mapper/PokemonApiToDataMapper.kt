package com.pokemon.datasource.api.mapper

import com.pokemon.data.model.PokemonDataModel
import com.pokemon.datasource.api.model.PokemonApiModel

class PokemonApiToDataMapper(
    private val pokemonInfoModelApiToDataMapper: PokemonInfoModelApiToDataMapper
) {
    fun map(model: PokemonApiModel): PokemonDataModel {
        return PokemonDataModel(
            pokemonList = model.results.map { pokemonInfoModelApiToDataMapper.map(it) }
        )
    }
}
