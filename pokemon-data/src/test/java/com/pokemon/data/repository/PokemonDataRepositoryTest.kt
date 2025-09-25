package com.pokemon.data.repository

import com.pokemon.data.datasource.PokemonRemoteSource
import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.data.mapper.PokemonInfoDetailModelToDomainMapper
import com.pokemon.data.model.PokemonInfoDataModel
import com.pokemon.data.model.PokemonInfoDetailDataModel
import com.pokemon.domain.model.PokemonInfoDomainModel
import com.pokemon.domain.model.PokemonInfoDetailDomainModel
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class PokemonDataRepositoryTest {

    private lateinit var repository: PokemonDataRepository
    private val mockDataSource = mock<PokemonRemoteSource>()
    private val mockInfoMapper = mock<PokemonInfoDataModelToDomainMapper>()
    private val mockDetailMapper = mock<PokemonInfoDetailModelToDomainMapper>()

    @Before
    fun setUp() {
        repository = PokemonDataRepository(
            mockDataSource,
            mockInfoMapper,
            mockDetailMapper
        )
    }

    @Test
    fun `pokemonList returns mapped pokemon list`() = runTest {
        // Given
        val dataModel1 = mock<PokemonInfoDataModel>()
        val dataModel2 = mock<PokemonInfoDataModel>()
        val dataList = listOf(dataModel1, dataModel2)
        whenever(mockDataSource.getRemotePokemonList()).thenReturn(dataList)

        val domainModel1 = mock<PokemonInfoDomainModel>()
        val domainModel2 = mock<PokemonInfoDomainModel>()
        whenever(mockInfoMapper.map(dataModel1)).thenReturn(domainModel1)
        whenever(mockInfoMapper.map(dataModel2)).thenReturn(domainModel2)

        // When
        val result = repository.pokemonList()

        // Then
        assertThat(result).containsExactly(domainModel1, domainModel2)
    }

    @Test
    fun `pokemonDetail returns mapped pokemon detail`() = runTest {
        // Given
        val pokemonId = 25
        val dataModel = mock<PokemonInfoDetailDataModel>()
        val domainModel = mock<PokemonInfoDetailDomainModel>()
        whenever(mockDataSource.getRemotePokemonDetail(pokemonId)).thenReturn(dataModel)
        whenever(mockDetailMapper.map(dataModel)).thenReturn(domainModel)

        // When
        val result = repository.pokemonDetail(pokemonId)

        // Then
        assertThat(result).isEqualTo(domainModel)
    }
}
