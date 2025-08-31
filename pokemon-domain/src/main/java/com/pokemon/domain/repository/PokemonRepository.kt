package com.pokemon.domain.repository

import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.model.PokemonInfoDomainModel

interface PokemonRepository {
    suspend fun pokemonList(): List<PokemonInfoDomainModel>
    suspend fun pokemonDetail(pokemonId: Int): PokemonInfoDetailDomainModel
}
