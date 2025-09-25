package com.pokemon.domain.repository

import com.pokemon.domain.model.PokemonDomainModel
import com.pokemon.domain.model.PokemonInfoDetailDomainModel

interface PokemonRepository {
    suspend fun pokemonList(offset: Int, limit: Int): PokemonDomainModel
    suspend fun pokemonDetail(pokemonId: Int): PokemonInfoDetailDomainModel
}
