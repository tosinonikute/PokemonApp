package com.pokemon.domain.model

data class PokemonDomainModel(
    val pokemonList: List<PokemonInfoDomainModel>,
    val count: Int
)
