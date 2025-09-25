package com.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.domain.usecase.GetPokemonListUseCase
import com.pokemon.presentation.mapper.PokemonDomainToPresentationMapper
import com.pokemon.presentation.mapper.PokemonInfoDomainToPresentationMapper
import com.pokemon.presentation.model.PokemonInfoPresentationModel
import com.pokemon.presentation.state.PaginationState
import com.pokemon.presentation.state.PokemonDetailPresentationState
import com.pokemon.presentation.state.PokemonPresentationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_OFFSET = 20
private const val PAGE_LIMIT = 20

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val pokemonDomainToPresentationMapper: PokemonDomainToPresentationMapper
) : ViewModel() {

    private val _pokemonPresentationState = MutableStateFlow<PokemonPresentationState>(PokemonPresentationState.Loading)
    val pokemonPresentationState: Flow<PokemonPresentationState> = _pokemonPresentationState.asStateFlow()

    private val _paginationState = MutableStateFlow(PaginationState())
    val paginationState: Flow<PaginationState> = _paginationState.asStateFlow()

    private var currentPokemonList = mutableListOf<PokemonInfoPresentationModel>()

    init {
        onLoadPokemonList()
    }

    fun onLoadPokemonList() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val initialOffset = 0
                val useCaseResult = getPokemonListUseCase.execute(0, PAGE_LIMIT)
                val pokemon =  pokemonDomainToPresentationMapper.map(useCaseResult)
                currentPokemonList.addAll(pokemon.pokemonList)

                // Compose doesn't detect this as a state change because the list reference itself hasn't changed
                // So you have to create a new instance
                _pokemonPresentationState.update {
                    PokemonPresentationState.Success(currentPokemonList.toList())
                }

                _paginationState.update {
                    it.copy(
                        currentPage = initialOffset,
                        totalPages = pokemon.count,
                        isLoading = false,
                        canLoadMore = initialOffset < pokemon.count
                    )
                }
            } catch (e: Exception) {
                _pokemonPresentationState.update { PokemonPresentationState.Error }
            }
        }
    }

    fun onLoadNextPage() {
        if (_paginationState.value.isLoading || !_paginationState.value.canLoadMore) {
            return
        }

        val nextPage = _paginationState.value.currentPage + PAGE_OFFSET
        _paginationState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val useCaseResult = getPokemonListUseCase.execute(nextPage, PAGE_LIMIT)
                val pokemon =  pokemonDomainToPresentationMapper.map(useCaseResult)
                currentPokemonList.addAll(pokemon.pokemonList)

                // Compose doesn't detect this as a state change because the list reference itself hasn't changed
                // So you have to create a new instance
                _pokemonPresentationState.update {
                    PokemonPresentationState.Success(currentPokemonList.toList())
                }

                _paginationState.update {
                    it.copy(
                        currentPage = nextPage,
                        isLoading = false,
                        canLoadMore = nextPage < it.totalPages
                    )
                }
            } catch (e: Exception) {
                _paginationState.update { it.copy(isLoading = false) }
                _pokemonPresentationState.update { PokemonPresentationState.Error }
            }
        }
    }
}
