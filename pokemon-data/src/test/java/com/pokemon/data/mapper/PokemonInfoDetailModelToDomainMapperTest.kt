package com.pokemon.data.mappers

import com.pokemon.data.mapper.PokemonInfoDetailModelToDomainMapper
import com.pokemon.data.model.PokemonInfoDetailDataModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonInfoDetailModelToDomainMapperTest {

    private lateinit var mapper: PokemonInfoDetailModelToDomainMapper

    @Before
    fun setUp() {
        mapper = PokemonInfoDetailModelToDomainMapper()
    }

    @Test
    fun `maps all fields correctly`() {
        // Given
        val dataModel = mock<PokemonInfoDetailDataModel>()
        whenever(dataModel.name).thenReturn("Pikachu")
        whenever(dataModel.id).thenReturn(25)
        whenever(dataModel.height).thenReturn(4)

        // When
        val result = mapper.map(dataModel)

        // Then
        assertThat(result.name).isEqualTo("Pikachu")
        assertThat(result.id).isEqualTo(25)
        assertThat(result.height).isEqualTo(4)
    }
}
