package com.pokemon.domain.repository

import com.pokemon.domain.model.PokemonInfoDomainModel

interface PokemonRepository {
    suspend fun pokemonList(): List<PokemonInfoDomainModel>
}
