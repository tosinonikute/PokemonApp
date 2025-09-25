package com.pokemon.domain.usecase

import com.pokemon.domain.model.PokemonDomainModel
import com.pokemon.domain.repository.PokemonRepository

class GetPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(offset: Int, limit: Int): PokemonDomainModel {
        return pokemonRepository.pokemonList(offset, limit)
    }
}
