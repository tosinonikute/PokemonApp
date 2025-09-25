package com.pokemon.data.mappers

import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.data.model.PokemonInfoDataModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonInfoDataModelToDomainMapperTest {

    private lateinit var mapper: PokemonInfoDataModelToDomainMapper

    @Before
    fun setUp() {
        mapper = PokemonInfoDataModelToDomainMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val dataModel = mock<PokemonInfoDataModel>()
        whenever(dataModel.name).thenReturn("Bulbasaur")
        whenever(dataModel.id).thenReturn(1)

        // When
        val result = mapper.map(dataModel)

        // Then
        assertThat(result.name).isEqualTo("Bulbasaur")
        assertThat(result.id).isEqualTo(1)
    }
}
