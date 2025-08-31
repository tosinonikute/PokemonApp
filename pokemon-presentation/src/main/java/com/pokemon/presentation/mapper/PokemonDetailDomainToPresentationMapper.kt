package com.pokemon.presentation.mapper

import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.presentation.model.PokemonInfoDetailPresentationModel
import com.pokemon.presentation.util.ImageConstants.POKEMON_IMAGE_BASE_URL


class PokemonDetailDomainToPresentationMapper {
    fun map(model: PokemonInfoDetailDomainModel): PokemonInfoDetailPresentationModel {
        return PokemonInfoDetailPresentationModel(
            name = model.name,
            id = model.id,
            height = model.height,
            imageUrl = getPokemonImageUrl(model.id)
        )
    }

    private fun getPokemonImageUrl(pokemonId: Int): String {
        return "$POKEMON_IMAGE_BASE_URL$pokemonId.png"
    }
}
