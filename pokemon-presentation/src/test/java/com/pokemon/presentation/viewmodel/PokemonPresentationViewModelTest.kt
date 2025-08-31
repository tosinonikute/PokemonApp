package com.pokemon.presentation.viewmodel

import com.pokemon.domain.usecase.GetPokemonDetailUseCase
import com.pokemon.presentation.mapper.PokemonDetailDomainToPresentationMapper
import com.pokemon.presentation.state.PokemonDetailPresentationState
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
class PokemonPresentationViewModelTest {

    @get:Rule
    val rule: TestRule = CoroutineTestRule(StandardTestDispatcher())

    private val getPokemonDetailUseCase: GetPokemonDetailUseCase = mock()
    private val pokemonDetailDomainToPresentationMapper: PokemonDetailDomainToPresentationMapper = mock()

    private lateinit var viewModel: PokemonDetailViewModel

    @Test
    fun `on init sets loading state`() = runTest {
        // When
        viewModel = PokemonDetailViewModel(
            getPokemonDetailUseCase = getPokemonDetailUseCase,
            pokemonDetailDomainToPresentationMapper = pokemonDetailDomainToPresentationMapper
        )

        // Then
        assertThat(viewModel.pokemonDetailPresentationState.first()).isEqualTo(
            PokemonDetailPresentationState.Loading)
    }

    @Test
    fun `on success shows pokemon details`() = runTest {
        // Given
        val pokemonId = 1
        val domainResult = PokemonPresentationTestData().pokemonDomainModel(pokemonId)
        val uiResult = PokemonPresentationTestData().pokemonPresentationModel(pokemonId)

        // When
        whenever(getPokemonDetailUseCase.execute(pokemonId)).thenReturn(domainResult)
        whenever(pokemonDetailDomainToPresentationMapper.map(model = domainResult)).thenReturn(uiResult)

        viewModel = PokemonDetailViewModel(getPokemonDetailUseCase, pokemonDetailDomainToPresentationMapper)
        viewModel.onGetPokemonDetail(pokemonId)
        advanceUntilIdle()

        // Then
        assertThat(viewModel.pokemonDetailPresentationState.first())
            .isEqualTo(PokemonDetailPresentationState.Success(uiResult))
    }

    @Test
    fun `on failure shows error`() = runTest {
        // Given
        val pokemonId = 1

        // When
        whenever(getPokemonDetailUseCase.execute(pokemonId)).thenAnswer { throw Exception("test_exception") }
        viewModel = PokemonDetailViewModel(getPokemonDetailUseCase, pokemonDetailDomainToPresentationMapper)
        viewModel.onGetPokemonDetail(pokemonId)
        advanceUntilIdle()

        // Then
        assertThat(viewModel.pokemonDetailPresentationState.first()).isEqualTo(
            PokemonDetailPresentationState.Error)
    }
}
