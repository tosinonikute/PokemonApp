package com.pokemon.ui.mapper

import com.pokemon.presentation.model.PokemonInfoPresentationModel
import com.pokemon.ui.model.PokemonInfoUiModel
import java.util.Locale

class PokemonPresentationToUiMapper {
    fun map(model: PokemonInfoPresentationModel): PokemonInfoUiModel {
        return PokemonInfoUiModel(
            name = model.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            },
            id = model.id
        )
    }
}
