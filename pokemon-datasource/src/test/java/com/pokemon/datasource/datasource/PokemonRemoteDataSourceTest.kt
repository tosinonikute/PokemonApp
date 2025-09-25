package com.pokemon.datasource.datasource

import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.datasource.api.datasource.PokemonRemoteDataSource
import com.pokemon.datasource.api.mapper.PokemonDetailModelApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDataMapper
import com.pokemon.datasource.api.model.PokemonApiModel
import com.pokemon.datasource.api.model.PokemonInfoApiModel
import com.pokemon.datasource.api.model.PokemonInfoDetailApiModel
import com.pokemon.datasource.api.service.PokemonApiService
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonRemoteDataSourceTest {

    private lateinit var dataSource: PokemonRemoteDataSource
    private val mockApiService = mock<PokemonApiService>()
    private val mockInfoMapper = mock<PokemonInfoModelApiToDataMapper>()
    private val mockDetailMapper = mock<PokemonDetailModelApiToDataMapper>()

    @Before
    fun setUp() {
        dataSource = PokemonRemoteDataSource(
            mockApiService,
            mockInfoMapper,
            mockDetailMapper
        )
    }

    @Test
    fun `getRemotePokemonList returns mapped pokemon list`() = runTest {
        // Given
        val apiModel1 = mock<PokemonInfoApiModel>()
        val apiModel2 = mock<PokemonInfoApiModel>()
        val pokemonApiModel = mock<PokemonApiModel>()
        whenever(pokemonApiModel.results).thenReturn(listOf(apiModel1, apiModel2))
        whenever(mockApiService.fetchPokemonList()).thenReturn(pokemonApiModel)

        val dataModel1 = mock<PokemonInfoDataModel>()
        val dataModel2 = mock<PokemonInfoDataModel>()
        whenever(mockInfoMapper.map(apiModel1)).thenReturn(dataModel1)
        whenever(mockInfoMapper.map(apiModel2)).thenReturn(dataModel2)

        // When
        val result = dataSource.getRemotePokemonList()

        // Then
        assertThat(result).containsExactly(dataModel1, dataModel2)
    }

    @Test
    fun `getRemotePokemonDetail returns mapped pokemon detail`() = runTest {
        // Given
        val pokemonId = 25
        val apiModel = mock<PokemonInfoDetailApiModel>()
        val dataModel = mock<PokemonInfoDetailDataModel>()
        whenever(mockApiService.fetchPokemonDetail(pokemonId)).thenReturn(apiModel)
        whenever(mockDetailMapper.map(apiModel)).thenReturn(dataModel)

        // When
        val result = dataSource.getRemotePokemonDetail(pokemonId)

        // Then
        assertThat(result).isEqualTo(dataModel)
    }
}
