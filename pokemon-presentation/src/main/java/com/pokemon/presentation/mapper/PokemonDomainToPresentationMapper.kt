package com.pokemon.presentation.mapper

import com.pokemon.domain.model.PokemonDomainModel
import com.pokemon.presentation.model.PokemonPresentationModel

class PokemonDomainToPresentationMapper(
    private val pokemonInfoDomainToPresentationMapper: PokemonInfoDomainToPresentationMapper
) {
    fun map(model: PokemonDomainModel): PokemonPresentationModel {
        return PokemonPresentationModel(
            pokemonList = model.pokemonList.map { pokemonInfoDomainToPresentationMapper.map(it) },
            count = model.count
        )
    }
}
