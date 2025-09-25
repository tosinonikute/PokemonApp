package com.pokemon.presentation.mapper

import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.presentation.model.PokemonInfoPresentationModel
import com.pokemon.presentation.util.ImageConstants.POKEMON_IMAGE_BASE_URL

class PokemonInfoDomainToPresentationMapper {
    fun map(model: PokemonInfoDomainModel): PokemonInfoPresentationModel {
        return PokemonInfoPresentationModel(
            name = model.name,
            id = model.id,
            imageUrl = getPokemonImageUrl(model.id)
        )
    }

    private fun getPokemonImageUrl(pokemonId: Int): String {
        return "$POKEMON_IMAGE_BASE_URL$pokemonId.png"
    }
}
