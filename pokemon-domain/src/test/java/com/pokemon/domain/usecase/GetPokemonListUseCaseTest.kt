package com.pokemon.domain.usecase

import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetPokemonListUseCaseTest {

    private lateinit var useCase: GetPokemonListUseCase
    private val mockRepository = mock<PokemonRepository>()

    @Before
    fun setUp() {
        useCase = GetPokemonListUseCase(mockRepository)
    }

    @Test
    fun `execute returns pokemon list from repository`() = runTest {
        // Given
        val mock = mock<PokemonInfoDomainModel>()
        val expectedList = listOf(
            mock,
            mock
        )
        whenever(mockRepository.pokemonList()).thenReturn(expectedList)

        // When
        val result = useCase.execute()

        // Then
        assertThat(result).isEqualTo(expectedList)
    }
}
