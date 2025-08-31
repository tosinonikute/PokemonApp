package com.pokemon.presentation.mapper

import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.presentation.model.PokemonInfoPresentationModel

class PokemonDomainToPresentationMapper {
    fun map(model: PokemonInfoDomainModel): PokemonInfoPresentationModel {
        return PokemonInfoPresentationModel(
            name = model.name,
            id = model.id
        )
    }
}
