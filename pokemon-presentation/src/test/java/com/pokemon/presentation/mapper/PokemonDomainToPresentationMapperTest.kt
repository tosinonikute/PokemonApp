package com.pokemon.presentation.mapper

import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.presentation.util.ImageConstants.POKEMON_IMAGE_BASE_URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonDomainToPresentationMapperTest {

    private lateinit var mapper: PokemonInfoDomainToPresentationMapper

    @Before
    fun setUp() {
        mapper = PokemonInfoDomainToPresentationMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val domainModel = mock<PokemonInfoDomainModel>()
        whenever(domainModel.name).thenReturn("Bulbasaur")
        whenever(domainModel.id).thenReturn(1)

        // When
        val result = mapper.map(domainModel)

        // Then
        assertThat(result.name).isEqualTo("Bulbasaur")
        assertThat(result.id).isEqualTo(1)
        assertThat(result.imageUrl).isEqualTo("${POKEMON_IMAGE_BASE_URL}1.png")
    }

    @Test
    fun `generates correct image URL for different pokemon ID`() {
        // Given
        val domainModel = mock<PokemonInfoDomainModel>()
        whenever(domainModel.name).thenReturn("Mew")
        whenever(domainModel.id).thenReturn(151)

        // When
        val result = mapper.map(domainModel)

        // Then
        assertThat(result.imageUrl).isEqualTo("${POKEMON_IMAGE_BASE_URL}151.png")
    }
}