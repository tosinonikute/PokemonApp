package com.pokemon.di

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.data.repository.PokemonDataRepository
import com.pokemon.domain.repository.PokemonRepository
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDataModule {

    @Provides
    fun providerPokemonApiToDataMapper(
        pokemonDataSource: PokemonDataSource,
        pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper
    ): PokemonRepository = PokemonDataRepository(
        pokemonDataSource,
        pokemonInfoDataModelToDomainMapper
    )

    @Provides
    fun providerPokemonInfoDataModelToDomainMapper(
    ) = PokemonInfoDataModelToDomainMapper()
}
