package com.pokemon.domain.usecase

import com.pokemon.domain.model.PokemonInfoDomainModel

class GetPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(): List<PokemonInfoDomainModel> {
        return pokemonRepository.pokemonList()
    }
}
