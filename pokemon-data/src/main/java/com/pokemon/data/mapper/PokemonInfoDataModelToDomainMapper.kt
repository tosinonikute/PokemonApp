package com.pokemon.data.mapper

import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.domain.model.PokemonInfoDomainModel

class PokemonInfoDataModelToDomainMapper {
    fun map(model: PokemonInfoDataModel): PokemonInfoDomainModel {
        return PokemonInfoDomainModel(
            name = model.name,
            id = model.id
        )
    }
}
