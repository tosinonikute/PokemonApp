package com.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.domain.usecase.GetPokemonDetailUseCase
import com.pokemon.presentation.mapper.PokemonDetailDomainToPresentationMapper
import com.pokemon.presentation.state.PokemonDetailPresentationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val pokemonDetailDomainToPresentationMapper: PokemonDetailDomainToPresentationMapper
) : ViewModel() {

    private val _pokemonDetailPresentationState = MutableStateFlow<PokemonDetailPresentationState>(
        PokemonDetailPresentationState.Loading
    )
    val pokemonDetailPresentationState: Flow<PokemonDetailPresentationState>
    = _pokemonDetailPresentationState.asStateFlow()

    fun onGetPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val pokemonDetailDomainModel = getPokemonDetailUseCase.execute(pokemonId)
                val pokemonPresentationDomainModel = pokemonDetailDomainToPresentationMapper.map(pokemonDetailDomainModel)
                val state = PokemonDetailPresentationState.Success(pokemonPresentationDomainModel)
                _pokemonDetailPresentationState.update { state }
            } catch (e: Exception) {
                _pokemonDetailPresentationState.update { PokemonDetailPresentationState.Error }
            }
        }
    }
}
