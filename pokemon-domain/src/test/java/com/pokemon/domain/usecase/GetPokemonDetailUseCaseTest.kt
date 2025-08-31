package com.pokemon.domain.usecase

import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetPokemonDetailUseCaseTest {

    private lateinit var useCase: GetPokemonDetailUseCase
    private val mockRepository = mock<PokemonRepository>()

    @Before
    fun setUp() {
        useCase = GetPokemonDetailUseCase(mockRepository)
    }

    @Test
    fun `execute returns pokemon detail from repository`() = runTest {
        // Given
        val pokemonId = 25
        val expectedDetail = mock<PokemonInfoDetailDomainModel>()
        whenever(mockRepository.pokemonDetail(pokemonId)).thenReturn(expectedDetail)

        // When
        val result = useCase.execute(pokemonId)

        // Then
        assertThat(result).isEqualTo(expectedDetail)
    }
}
