package com.pokemon.ui.mapper

import com.pokemon.presentation.model.PokemonInfoDetailPresentationModel
import com.pokemon.ui.model.PokemonInfoDetailUiModel
import java.util.Locale

class PokemonDetailPresentationToUiMapper {
    fun map(model: PokemonInfoDetailPresentationModel): PokemonInfoDetailUiModel {
        return PokemonInfoDetailUiModel(
            name = model.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            },
            id = model.id,
            height = model.height,
            imageUrl = model.imageUrl
        )
    }
}
