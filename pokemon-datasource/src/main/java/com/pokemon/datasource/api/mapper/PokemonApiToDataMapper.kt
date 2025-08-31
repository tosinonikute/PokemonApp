package com.pokemon.datasource.api.mapper

import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.datasource.api.model.PokemonInfoApiModel

class PokemonApiToDataMapper(
    private val pokemonInfoModelApiToDataMapper: PokemonInfoModelApiToDataMapper
) {
    fun map(model: List<PokemonInfoApiModel>): List<PokemonInfoDataModel> {
        return model.map {
            pokemonInfoModelApiToDataMapper.map(it)
        }
    }
}
