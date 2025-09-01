package com.pokemon.datasource.api.mapper

import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.datasource.api.model.PokemonInfoApiModel

class PokemonInfoModelApiToDataMapper {
    fun map(model: PokemonInfoApiModel): PokemonInfoDataModel {
        return PokemonInfoDataModel(
            name = model.name.orEmpty(),
            id = extractIdFromUrl(model.url.orEmpty())
        )
    }

    private fun extractIdFromUrl(url: String): Int {
        return if (url.isBlank()) {
            0
        } else {
            try {
                url.removeSuffix("/").split("/").last().toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
    }
}
