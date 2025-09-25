package com.pokemon.datasource.api.mapper

import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.datasource.api.model.PokemonInfoDatabaseModel

class PokemonInfoModelDatabaseToDataMapper {
    fun map(model: PokemonInfoDatabaseModel): PokemonInfoDataModel {
        return PokemonInfoDataModel(
            name = model.name.orEmpty(),
            id = model.id ?: 0
        )
    }
}
