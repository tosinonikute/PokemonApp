package com.pokemon.data.mapper

import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.domain.model.PokemonInfoDetailDomainModel

class PokemonInfoDetailModelToDomainMapper {
    fun map(model: PokemonInfoDetailDataModel): PokemonInfoDetailDomainModel {
        return PokemonInfoDetailDomainModel(
            name = model.name,
            id = model.id,
            height = model.height
        )
    }
}
