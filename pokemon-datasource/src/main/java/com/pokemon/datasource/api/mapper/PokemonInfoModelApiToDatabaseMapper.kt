package com.pokemon.datasource.api.mapper

import com.pokemon.datasource.api.model.PokemonInfoApiModel
import com.pokemon.datasource.api.model.PokemonInfoDatabaseModel

class PokemonInfoModelApiToDatabaseMapper {
    fun map(model: PokemonInfoApiModel): PokemonInfoDatabaseModel {
        return PokemonInfoDatabaseModel(
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
