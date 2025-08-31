package com.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.domain.usecase.GetPokemonListUseCase
import com.pokemon.presentation.mapper.PokemonDomainToPresentationMapper
import com.pokemon.presentation.state.PokemonPresentationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val pokemonDomainToPresentationMapper: PokemonDomainToPresentationMapper
) : ViewModel() {

    private val _pokemonPresentationState = MutableStateFlow<PokemonPresentationState>(PokemonPresentationState.Loading)
    val pokemonPresentationState: Flow<PokemonPresentationState> = _pokemonPresentationState

    init {
        viewModelScope.launch {

        }
    }
}
