package com.pokemon.presentation.viewmodel

import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.presentation.model.PokemonInfoDetailPresentationModel
import com.pokemon.presentation.model.PokemonInfoPresentationModel
import com.pokemon.presentation.util.ImageConstants.POKEMON_IMAGE_BASE_URL

class PokemonPresentationTestData {
    fun pokemonPresentationModelList(): List<PokemonInfoPresentationModel> {
        return listOf(
            PokemonInfoPresentationModel(
                id = 1,
                name = "bulbasaur",
                imageUrl = "${POKEMON_IMAGE_BASE_URL}1.png"
            ),
            PokemonInfoPresentationModel(
                id = 2,
                name = "ivysaur",
                imageUrl = "${POKEMON_IMAGE_BASE_URL}2.png"
            ),
            PokemonInfoPresentationModel(
                id = 3,
                name = "venusaur",
                imageUrl = "${POKEMON_IMAGE_BASE_URL}3.png"
            )
        )
    }

    fun pokemonDomainModelList(): List<PokemonInfoDomainModel> {
        return listOf(
            PokemonInfoDomainModel(
                id = 1,
                name = "bulbasaur"
            ),
            PokemonInfoDomainModel(
                id = 2,
                name = "ivysaur"
            ),
            PokemonInfoDomainModel(
                id = 3,
                name = "venusaur"
            )
        )
    }

    fun pokemonDomainModel(id: Int): PokemonInfoDetailDomainModel {
        return PokemonInfoDetailDomainModel(
            id = id,
            name = "bulbasaur",
            height = 7
        )
    }

    fun pokemonPresentationModel(id: Int): PokemonInfoDetailPresentationModel {
        return PokemonInfoDetailPresentationModel(
            id = id,
            name = "bulbasaur",
            height = 7,
            imageUrl = "$POKEMON_IMAGE_BASE_URL$id.png"
        )
    }
}
