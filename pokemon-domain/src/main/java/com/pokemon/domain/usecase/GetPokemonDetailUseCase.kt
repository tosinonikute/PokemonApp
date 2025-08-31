package com.pokemon.domain.usecase

import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.repository.PokemonRepository

class GetPokemonDetailUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(pokemonId: Int): PokemonInfoDetailDomainModel {
        return pokemonRepository.pokemonDetail(pokemonId)
    }
}
