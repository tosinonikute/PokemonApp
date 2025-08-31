package com.pokemon.datasource.api.mapper

import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.datasource.api.model.PokemonInfoDetailApiModel

class PokemonDetailModelApiToDataMapper {
    fun map(model: PokemonInfoDetailApiModel): PokemonInfoDetailDataModel {
        return PokemonInfoDetailDataModel(
            name = model.name.orEmpty(),
            id = model.id ?: 0,
            height = model.height ?: 0
        )
    }
}
