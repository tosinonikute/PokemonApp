package com.pokemon.presentation.viewmodel

import com.pokemon.domain.usecase.GetPokemonListUseCase
import com.pokemon.presentation.mapper.PokemonInfoDomainToPresentationMapper
import com.pokemon.presentation.state.PokemonPresentationState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListViewModelTest {

    @get:Rule
    val rule: TestRule = CoroutineTestRule(StandardTestDispatcher())

    private val getPokemonListUseCase: GetPokemonListUseCase = mock()

    private val pokemonInfoDomainToPresentationMapper: PokemonInfoDomainToPresentationMapper = mock()

    private lateinit var viewModel: PokemonListViewModel

    @Test
    fun `on init sets loading state`() = runTest {
        // When
        viewModel = PokemonListViewModel(getPokemonListUseCase, pokemonInfoDomainToPresentationMapper)

        // Then
        assertThat(viewModel.pokemonPresentationState.first()).isEqualTo(PokemonPresentationState.Loading)
    }

    @Test
    fun `on success show success state with pokemon list`() = runTest {
        // Given
        val domainResults = PokemonPresentationTestData().pokemonDomainModelList()
        val uiResult = PokemonPresentationTestData().pokemonPresentationModelList()

        // When
        whenever(getPokemonListUseCase.execute()).thenReturn(domainResults)
        whenever(pokemonInfoDomainToPresentationMapper.map(model = domainResults[0])).thenReturn(uiResult[0])
        whenever(pokemonInfoDomainToPresentationMapper.map(model = domainResults[1])).thenReturn(uiResult[1])
        whenever(pokemonInfoDomainToPresentationMapper.map(model = domainResults[2])).thenReturn(uiResult[2])

        viewModel = PokemonListViewModel(getPokemonListUseCase, pokemonInfoDomainToPresentationMapper)
        viewModel.onLoadPokemonList()
        advanceUntilIdle()

        // Then
        assertThat(viewModel.pokemonPresentationState.first()).isEqualTo(PokemonPresentationState.Success(uiResult))
    }

    @Test
    fun `on failure shows error`() = runTest {
        // When
        whenever(getPokemonListUseCase.execute()).thenAnswer { throw Exception("test_exception") }
        viewModel = PokemonListViewModel(getPokemonListUseCase, pokemonInfoDomainToPresentationMapper)
        viewModel.onLoadPokemonList()
        advanceUntilIdle()

        // Then
        assertThat(viewModel.pokemonPresentationState.first()).isEqualTo(PokemonPresentationState.Error)
    }
}
