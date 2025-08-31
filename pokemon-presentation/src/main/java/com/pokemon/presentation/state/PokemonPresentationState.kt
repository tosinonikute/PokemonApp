package com.pokemon.presentation.state

import com.pokemon.presentation.model.PokemonInfoPresentationModel

sealed interface PokemonPresentationState {
    data object Loading : PokemonPresentationState
    data class Success(val pokemonList: List<PokemonInfoPresentationModel>) : PokemonPresentationState
    data object Error : PokemonPresentationState
}
