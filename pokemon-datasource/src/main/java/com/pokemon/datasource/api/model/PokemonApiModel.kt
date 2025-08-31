package com.pokemon.datasource.api.model

data class PokemonApiModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonInfoApiModel>
)
