package com.pokemon.datasource.mapper

import com.pokemon.datasource.api.mapper.PokemonDetailModelApiToDataMapper
import com.pokemon.datasource.api.model.PokemonInfoDetailApiModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonDetailModelApiToDataMapperTest {

    private lateinit var mapper: PokemonDetailModelApiToDataMapper

    @Before
    fun setUp() {
        mapper = PokemonDetailModelApiToDataMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val apiModel = mock<PokemonInfoDetailApiModel>()
        whenever(apiModel.name).thenReturn("Pikachu")
        whenever(apiModel.id).thenReturn(25)
        whenever(apiModel.height).thenReturn(4)

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.name).isEqualTo("Pikachu")
        assertThat(result.id).isEqualTo(25)
        assertThat(result.height).isEqualTo(4)
    }

    @Test
    fun `handles null name`() {
        // Given
        val apiModel = mock<PokemonInfoDetailApiModel>()
        whenever(apiModel.name).thenReturn(null)
        whenever(apiModel.id).thenReturn(25)
        whenever(apiModel.height).thenReturn(4)

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.name).isEmpty()
    }

    @Test
    fun `handles null id`() {
        // Given
        val apiModel = mock<PokemonInfoDetailApiModel>()
        whenever(apiModel.name).thenReturn("Pikachu")
        whenever(apiModel.id).thenReturn(null)
        whenever(apiModel.height).thenReturn(4)

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.id).isEqualTo(0)
    }

    @Test
    fun `handles null height`() {
        // Given
        val apiModel = mock<PokemonInfoDetailApiModel>()
        whenever(apiModel.name).thenReturn("Pikachu")
        whenever(apiModel.id).thenReturn(25)
        whenever(apiModel.height).thenReturn(null)

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.height).isEqualTo(0)
    }
}
