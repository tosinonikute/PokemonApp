package com.pokemon.ui.mapper

import com.pokemon.presentation.model.PokemonInfoDetailPresentationModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonDetailPresentationToUiMapperTest {

    private lateinit var mapper: PokemonDetailPresentationToUiMapper

    @Before
    fun setUp() {
        mapper = PokemonDetailPresentationToUiMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val presentationModel = mock<PokemonInfoDetailPresentationModel>()
        whenever(presentationModel.name).thenReturn("pikachu")
        whenever(presentationModel.id).thenReturn(25)
        whenever(presentationModel.height).thenReturn(4)
        whenever(presentationModel.imageUrl).thenReturn("https://example.com/25.png")

        // When
        val result = mapper.map(presentationModel)

        // Then
        assertThat(result.name).isEqualTo("Pikachu")
        assertThat(result.id).isEqualTo(25)
        assertThat(result.height).isEqualTo(0.4)
        assertThat(result.imageUrl).isEqualTo("https://example.com/25.png")
    }

    @Test
    fun `capitalizes first letter of name correctly`() {
        // Given
        val presentationModel = mock<PokemonInfoDetailPresentationModel>()
        whenever(presentationModel.name).thenReturn("bulbasaur")
        whenever(presentationModel.id).thenReturn(1)
        whenever(presentationModel.height).thenReturn(7)
        whenever(presentationModel.imageUrl).thenReturn("https://example.com/1.png")

        // When
        val result = mapper.map(presentationModel)

        // Then
        assertThat(result.name).isEqualTo("Bulbasaur")
    }

    @Test
    fun `handles already capitalized name`() {
        // Given
        val presentationModel = mock<PokemonInfoDetailPresentationModel>()
        whenever(presentationModel.name).thenReturn("Charizard")
        whenever(presentationModel.id).thenReturn(6)
        whenever(presentationModel.height).thenReturn(17)
        whenever(presentationModel.imageUrl).thenReturn("https://example.com/6.png")

        // When
        val result = mapper.map(presentationModel)

        // Then
        assertThat(result.name).isEqualTo("Charizard")
    }
}
