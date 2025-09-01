package com.pokemon.datasource.mapper

import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDataMapper
import com.pokemon.datasource.api.model.PokemonInfoApiModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonInfoModelApiToDataMapperTest {

    private lateinit var mapper: PokemonInfoModelApiToDataMapper

    @Before
    fun setUp() {
        mapper = PokemonInfoModelApiToDataMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val apiModel = mock<PokemonInfoApiModel>()
        whenever(apiModel.name).thenReturn("Bulbasaur")
        whenever(apiModel.url).thenReturn("https://pokeapi.co/api/v2/pokemon/1/")

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.name).isEqualTo("Bulbasaur")
        assertThat(result.id).isEqualTo(1)
    }

    @Test
    fun `extracts id from url correctly`() {
        // Given
        val apiModel = mock<PokemonInfoApiModel>()
        whenever(apiModel.name).thenReturn("Pikachu")
        whenever(apiModel.url).thenReturn("https://pokeapi.co/api/v2/pokemon/25/")

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.id).isEqualTo(25)
    }

    @Test
    fun `handles null name`() {
        // Given
        val apiModel = mock<PokemonInfoApiModel>()
        whenever(apiModel.name).thenReturn(null)
        whenever(apiModel.url).thenReturn("https://pokeapi.co/api/v2/pokemon/1/")

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.name).isEmpty()
    }

    @Test
    fun `handles null url`() {
        // Given
        val apiModel = mock<PokemonInfoApiModel>()
        whenever(apiModel.name).thenReturn("Bulbasaur")
        whenever(apiModel.url).thenReturn(null)

        // When
        val result = mapper.map(apiModel)

        // Then
        assertThat(result.id).isEqualTo(0)
    }
}
