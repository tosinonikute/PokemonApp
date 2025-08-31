package com.pokemon.presentation.mapper

import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import com.pokemon.presentation.util.ImageConstants.POKEMON_IMAGE_BASE_URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonDetailDomainToPresentationMapperTest {

    private lateinit var mapper: PokemonDetailDomainToPresentationMapper

    @Before
    fun setUp() {
        mapper = PokemonDetailDomainToPresentationMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val domainModel = mock<PokemonInfoDetailDomainModel>()
        whenever(domainModel.name).thenReturn("Pikachu")
        whenever(domainModel.id).thenReturn(25)
        whenever(domainModel.height).thenReturn(4)

        // When
        val result = mapper.map(domainModel)

        // Then
        assertThat(result.name).isEqualTo("Pikachu")
        assertThat(result.id).isEqualTo(25)
        assertThat(result.height).isEqualTo(4)
        assertThat(result.imageUrl).isEqualTo("${POKEMON_IMAGE_BASE_URL}25.png")
    }

    @Test
    fun `generates correct image URL for different pokemon ID`() {
        // Given
        val domainModel = mock<PokemonInfoDetailDomainModel>()
        whenever(domainModel.name).thenReturn("Charizard")
        whenever(domainModel.id).thenReturn(6)
        whenever(domainModel.height).thenReturn(17)

        // When
        val result = mapper.map(domainModel)

        // Then
        assertThat(result.imageUrl).isEqualTo("${POKEMON_IMAGE_BASE_URL}6.png")
    }
}