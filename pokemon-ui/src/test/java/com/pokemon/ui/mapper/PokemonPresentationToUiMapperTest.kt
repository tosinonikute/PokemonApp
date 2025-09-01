package com.pokemon.ui.mapper

import com.pokemon.presentation.model.PokemonInfoPresentationModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonPresentationToUiMapperTest {

    private lateinit var mapper: PokemonPresentationToUiMapper

    @Before
    fun setUp() {
        mapper = PokemonPresentationToUiMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val presentationModel = mock<PokemonInfoPresentationModel>()
        whenever(presentationModel.name).thenReturn("bulbasaur")
        whenever(presentationModel.id).thenReturn(1)
        whenever(presentationModel.imageUrl).thenReturn("https://example.com/1.png")

        // When
        val result = mapper.map(presentationModel)

        // Then
        assertThat(result.name).isEqualTo("Bulbasaur")
        assertThat(result.id).isEqualTo(1)
        assertThat(result.imageUrl).isEqualTo("https://example.com/1.png")
    }

    @Test
    fun `capitalizes first letter of name correctly`() {
        // Given
        val presentationModel = mock<PokemonInfoPresentationModel>()
        whenever(presentationModel.name).thenReturn("pikachu")
        whenever(presentationModel.id).thenReturn(25)
        whenever(presentationModel.imageUrl).thenReturn("https://example.com/25.png")

        // When
        val result = mapper.map(presentationModel)

        // Then
        assertThat(result.name).isEqualTo("Pikachu")
    }

    @Test
    fun `handles already capitalized name`() {
        // Given
        val presentationModel = mock<PokemonInfoPresentationModel>()
        whenever(presentationModel.name).thenReturn("Charizard")
        whenever(presentationModel.id).thenReturn(6)
        whenever(presentationModel.imageUrl).thenReturn("https://example.com/6.png")

        // When
        val result = mapper.map(presentationModel)

        // Then
        assertThat(result.name).isEqualTo("Charizard")
    }
}
