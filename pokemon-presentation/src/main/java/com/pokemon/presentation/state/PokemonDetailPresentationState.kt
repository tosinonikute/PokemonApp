package com.pokemon.presentation.state

import com.pokemon.presentation.model.PokemonInfoDetailPresentationModel

sealed interface PokemonDetailPresentationState {
    data object Loading : PokemonDetailPresentationState
    data class Success(val pokemonDetail: PokemonInfoDetailPresentationModel) : PokemonDetailPresentationState
    data object Error : PokemonDetailPresentationState
}
